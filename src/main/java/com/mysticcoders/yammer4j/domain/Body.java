/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 26, 2009
 * Time: 9:44:54 PM
 */
package com.mysticcoders.yammer4j.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class Body {

    private String plain;
    private String parsed;

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String getParsed() {
        return parsed;
    }

    public void setParsed(String parsed) {
        this.parsed = parsed;
    }

    @Override
    public String toString() {
        return "YammerBody{" +
                "plain='" + plain + '\'' +
                ", parsed='" + parsed + '\'' +
                '}';
    }
}