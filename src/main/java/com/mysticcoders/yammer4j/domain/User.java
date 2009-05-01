package com.mysticcoders.yammer4j.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class User {

    private Long id;

    private String timezone;
    private String name;

    @XStreamAlias("full-name")
    private String fullName;

    @XStreamAlias("web-url")
    private String webUrl;

    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "YammerUser{" +
                "id=" + id +
                ", timezone='" + timezone + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    /*

      <response>
        <timezone>US/Pacific</timezone>
        <job-title>Owner</job-title>
        <type>user</type>
        <contact>
          <im>
            <provider>aim</provider>
            <username>nlpjunke</username>
          </im>
          <email-addresses>
            <email-address>
              <type>primary</type>
              <address>andrew@mysticcoders.com</address>
            </email-address>
          </email-addresses>
          <phone-numbers>
            <phone-number>
              <type>work</type>
              <number>949-528-6480</number>
            </phone-number>
            <phone-number>
              <type>mobile</type>
              <number>714-697-8046</number>
            </phone-number>
          </phone-numbers>
        </contact>
        <network-name>mysticcoders.com</network-name>
        <network-id>7495</network-id>
        <name>andrew</name>
        <id>40280</id>
        <birth-date>September 10</birth-date>
        <web-url>https://www.yammer.com/users/andrew</web-url>
        <network-domains>
          <network-domain>mysticcoders.com</network-domain>
        </network-domains>
        <stats>
          <updates>66</updates>
          <following>4</following>
          <followers>2</followers>
        </stats>
        <previous-companies/>
        <hire-date>2000-01-01</hire-date>
        <mugshot-url>https://assets1.yammer.com/user_uploaded/photos/p1/0003/2425/Andy_small.png</mugshot-url>
        <external-urls/>
        <full-name>Andrew Lombardi</full-name>
        <schools/>
        <url>https://www.yammer.com/api/v1/users/40280</url>
        <location>Orange County</location>
        <state>active</state>
      </response>


    */
}