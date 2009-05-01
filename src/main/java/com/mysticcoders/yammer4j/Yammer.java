/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 26, 2009
 * Time: 9:36:49 PM
 */
package com.mysticcoders.yammer4j;

import com.mysticcoders.yammer4j.domain.Config;
import com.mysticcoders.yammer4j.domain.Message;
import com.mysticcoders.yammer4j.domain.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;
import net.oauth.*;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient4.HttpClient4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Yammer {

    private static final Logger log = LoggerFactory.getLogger(Yammer.class);

    private Config config;

    private String consumerKey = "IU4o1fV9KNvZjJUTC8ss5Q";
    private String consumerSecret = "q6niVj8DUclip9AravZ8MJe7dIPi556RBTQXyOl27w";

    private String usersUrl = "https://www.yammer.com/api/v1/users.xml";
    private String messagesUrl = "https://www.yammer.com/api/v1/messages.xml";
    private String requestUrl = "https://www.yammer.com/oauth/request_token";
    private String accessUrl = "https://www.yammer.com/oauth/access_token";
    private String authUrl = "https://www.yammer.com/oauth/authorize";

    private String tokenSecret;

    /**
     * 
     *
     * @param config
     */
    public Yammer(Config config) {
        this.config = config;
    }

    public Yammer() { }
    
    /**
     * @return the register URL
     */
    public String register() {
        try {
            OAuthAccessor accessor = createOAuthAccessor();

            OAuthClient client = new OAuthClient(new HttpClient4());
            client.getRequestToken(accessor);

            StringBuilder sb = new StringBuilder();
            sb.append("requestToken:").append(accessor.requestToken);
            sb.append(":tokenSecret:").append(accessor.tokenSecret);

            config.setPreOauthToken(accessor.requestToken);
            config.setPreOauthTokenSecret(accessor.tokenSecret);

            return authUrl + "?oauth_token=" + accessor.requestToken;

        } catch (OAuthException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean authorize() {

        try {

            Properties paramProps = new Properties();
            paramProps.setProperty("oauth_token", config.getPreOauthToken());

            tokenSecret = config.getPreOauthTokenSecret();

            paramProps.setProperty("application_name", "javabot yammer plugin");
            paramProps.setProperty("callback_token", config.getCallbackToken());
            
            OAuthMessage response = sendRequest(paramProps,
                    accessUrl);

            String responseBody = response.readBodyAsString();

            System.out.println(responseBody);

            // TODO need to actually do validation on this!
            String[] separatedParameters = responseBody.split("&");
            String[] oauthTokenPair = separatedParameters[0].split("=");
            String[] oauthTokenSecretPair = separatedParameters[1].split("=");

            config.setOauthToken(oauthTokenPair[1]);
            config.setOauthTokenSecret(oauthTokenSecretPair[1]);

        } catch (OAuthException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * So, the data model is a little screwy, have to think it through
     */
/*
    public void updateMessages() {
        System.out.println("running updatemessage: " + Calendar.getInstance().getTime());
        for (Config config : dao.list()) {

            Message message = messageDao.latest();

            log.debug("updateMessages.lastMessageId: " + message.getId());

            List<Message> messages = getMessages(config, message.getId());

            if (messages != null && messages.size() > 0) {

                log.debug("Message count: " + messages.size());
                for (int i = messages.size() - 1; i >= 0; i--) {
                    Message message = messages.get(i);

                    log.debug("Message[" + i + "]: " + message);

                    User user = userDao.find(message.getSenderId());
                }

                log.debug("Updated. " + Calendar.getInstance().getTime());
            }
        }
    }
*/

    protected List<Message> getMessages(Long lastId) {
        List<Message> messages = null;

        try {
            Properties paramProps = new Properties();

            paramProps.setProperty("oauth_token", config.getOauthToken());

            if (lastId != null) {
                paramProps.setProperty("newer_than", "" + lastId);
            }

            tokenSecret = config.getOauthTokenSecret();

            OAuthMessage response = sendRequest(paramProps, "GET",
                    messagesUrl);

            String responseBody = response.readBodyAsString();

            try {
                XStream xstream = new XStream(new DomDriver());

                xstream.processAnnotations(YammerResponse.class);

                xstream.omitField(YammerResponse.class, "meta");
                xstream.omitField(YammerResponse.class, "references");

                YammerResponse responseObj = (YammerResponse) xstream.fromXML(responseBody);

                return responseObj.getMessages();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (OAuthException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return messages;
    }

    protected List<Message> getMessages() {
        return getMessages(null);
    }

    protected List<User> getUserList() {

        List<User> users = null;

        try {

            Properties paramProps = new Properties();

            paramProps.setProperty("oauth_token", config.getOauthToken());

            tokenSecret = config.getOauthTokenSecret();

            OAuthMessage response = sendRequest(paramProps, "GET",
                    usersUrl);

            String responseBody = response.readBodyAsString();

            System.out.println("responseBody:" + responseBody);

            try {
                XStream xstream = new XStream(new DomDriver());

                xstream.processAnnotations(UserResponse.class);

                xstream.omitField(User.class, "timezone");
                xstream.omitField(User.class, "job-title");
                xstream.omitField(User.class, "type");
                xstream.omitField(User.class, "contact");
                xstream.omitField(User.class, "network-name");
                xstream.omitField(User.class, "network-id");
                xstream.omitField(User.class, "birth-date");
                xstream.omitField(User.class, "network-domains");
                xstream.omitField(User.class, "stats");
                xstream.omitField(User.class, "previous-companies");
                xstream.omitField(User.class, "hire-date");
                xstream.omitField(User.class, "mugshot-url");
                xstream.omitField(User.class, "external-urls");
                xstream.omitField(User.class, "schools");
                xstream.omitField(User.class, "url");
                xstream.omitField(User.class, "location");
                xstream.omitField(User.class, "job-title");
                xstream.omitField(User.class, "kids-names");
                xstream.omitField(User.class, "significant-other");
                xstream.omitField(User.class, "interests");

                UserResponse userResponseObj = (UserResponse) xstream.fromXML(responseBody);

                return userResponseObj.getUsers();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (OAuthException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return users;
    }

    private OAuthAccessor createOAuthAccessor() {
        String callbackUrl = null;

        OAuthServiceProvider provider
                = new OAuthServiceProvider(requestUrl, authUrl, accessUrl);
        OAuthConsumer consumer
                = new OAuthConsumer(callbackUrl, config.getConsumerKey(),
                config.getConsumerSecret(), provider);
        return new OAuthAccessor(consumer);
    }

    private OAuthMessage sendRequest(Map map, String url) throws IOException,
            URISyntaxException, OAuthException {
        return sendRequest(map, "POST", url);
    }

    private OAuthMessage sendRequest(Map map, String method, String url) throws IOException,
            URISyntaxException, OAuthException {

        List<Map.Entry> params = new ArrayList<Map.Entry>();
        for (Object o : map.entrySet()) {
            Map.Entry p = (Map.Entry) o;
            params.add(new OAuth.Parameter((String) p.getKey(),
                    (String) p.getValue()));
        }
        OAuthAccessor accessor = createOAuthAccessor();
        accessor.tokenSecret = tokenSecret;

        accessor.consumer.setProperty(OAuthClient.PARAMETER_STYLE, OAuthClient.ParameterStyle.AUTHORIZATION_HEADER);

        OAuthClient client = new OAuthClient(new HttpClient4());
        return client.invoke(accessor, method, url, params);
    }


    @XStreamAlias("response")
    class YammerResponse {

        private List<Message> messages;

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }
    }

    @XStreamAlias("response")
    class UserResponse {

        @XStreamImplicit(itemFieldName = "response")
        private List<User> users;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

}