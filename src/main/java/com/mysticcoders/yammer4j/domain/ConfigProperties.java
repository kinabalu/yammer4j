/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 30, 2009
 * Time: 4:37:54 PM
 */
package com.mysticcoders.yammer4j.domain;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties implements Config {

    private Properties props;

    public ConfigProperties(InputStream is) throws IOException {
        props = new Properties();
        props.load(is);

        is.close();
    }

    public void writeProperties(Properties props) {
        //nop
    }

    @Override
    public String getConsumerKey() {
        return props.getProperty("consumer.key");
    }

    @Override
    public void setConsumerKey(String consumerKey) throws IOException {
        props.setProperty("consumer.key", consumerKey);
        writeProperties(props);
    }

    @Override
    public String getConsumerSecret() {
        return props.getProperty("consumer.secret");
    }

    @Override
    public void setConsumerSecret(String consumerSecret) throws IOException  {
        props.setProperty("consumer.secret", consumerSecret);
        writeProperties(props);
    }

    @Override
    public String getCallbackToken() {
        return props.getProperty("callback.token");
    }

    @Override
    public void setCallbackToken(String callbackToken) throws IOException  {
        props.setProperty("callback.token", callbackToken);
        writeProperties(props);
    }

    @Override
    public String getPreOauthToken() {
        return props.getProperty("pre.oauth.token");
    }

    @Override
    public void setPreOauthToken(String preOauthToken) throws IOException  {
        props.setProperty("pre.oauth.token", preOauthToken);
        writeProperties(props);
    }

    @Override
    public String getPreOauthTokenSecret() {
        return props.getProperty("pre.oauth.token.secret");
    }

    @Override
    public void setPreOauthTokenSecret(String preOauthTokenSecret) throws IOException  {
        props.setProperty("pre.oauth.token.secret", preOauthTokenSecret);
        writeProperties(props);
    }

    @Override
    public String getOauthToken() {
        return props.getProperty("oauth.token");
    }

    @Override
    public void setOauthToken(String oauthToken) throws IOException  {
        props.setProperty("oauth.token", oauthToken);
        writeProperties(props);
    }

    @Override
    public String getOauthTokenSecret() {
        return props.getProperty("oauth.token.secret");
    }

    @Override
    public void setOauthTokenSecret(String oauthTokenSecret) throws IOException  {
        props.setProperty("oauth.token.secret", oauthTokenSecret);
        writeProperties(props);
    }

}