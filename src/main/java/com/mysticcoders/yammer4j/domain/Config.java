/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 26, 2009
 * Time: 9:46:11 PM
 */
package com.mysticcoders.yammer4j.domain;

import java.io.IOException;

public interface Config {

    public String getConsumerKey();
    public String getConsumerSecret();
    public String getCallbackToken();
    public String getPreOauthToken();
    public String getPreOauthTokenSecret();
    public String getOauthToken();
    public String getOauthTokenSecret();

    public void setConsumerKey(String consumerKey) throws IOException;

    public void setConsumerSecret(String consumerSecret) throws IOException;

    public void setCallbackToken(String callbackToken) throws IOException;

    public void setPreOauthToken(String preOauthToken) throws IOException;

    public void setPreOauthTokenSecret(String preOauthTokenSecret) throws IOException;

    public void setOauthToken(String oauthToken) throws IOException;

    public void setOauthTokenSecret(String oauthTokenSecret) throws IOException;

}