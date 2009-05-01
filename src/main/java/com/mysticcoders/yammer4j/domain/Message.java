/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 26, 2009
 * Time: 9:48:25 PM
 */
package com.mysticcoders.yammer4j.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;


/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 11, 2009
 * Time: 11:37:59 AM
 */

@XStreamAlias("message")
public class Message {

    private Long id;

    @XStreamAlias("client-url")
    private String clientUrl;

    @XStreamAlias("system-message")
    private String systemMessage;

    @XStreamAlias("group-id")
    private Long groupId;

    @XStreamAlias("direct-to-id")
    private Long directToId;
    private String url;

    @XStreamAlias("web-url")
    private String webUrl;

    @XStreamAlias("replied-to-id")
    private String repliedToId;

    @XStreamAlias("thread-id")
    private Long threadId;

    private Body body;

    private List<Attachment> attachments;

    @XStreamAlias("message-type")
    private String messageType;

    @XStreamAlias("client-type")
    private String clientType;

    @XStreamAlias("sender-id")
    private Long senderId;

    @XStreamAlias("sender-type")
    private String senderType;

    @XStreamAlias("created-at")
    private String createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDirectToId() {
        return directToId;
    }

    public void setDirectToId(Long directToId) {
        this.directToId = directToId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getRepliedToId() {
        return repliedToId;
    }

    public void setRepliedToId(String repliedToId) {
        this.repliedToId = repliedToId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "YammerMessage{" +
                "id=" + id +
                ", clientUrl='" + clientUrl + '\'' +
                ", systemMessage='" + systemMessage + '\'' +
                ", groupId=" + groupId +
                ", directToId=" + directToId +
                ", url='" + url + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", repliedToId='" + repliedToId + '\'' +
                ", threadId=" + threadId +
                ", body=" + body +
                ", attachments=" + attachments +
                ", messageType='" + messageType + '\'' +
                ", clientType='" + clientType + '\'' +
                ", senderId=" + senderId +
                ", senderType='" + senderType + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    @XStreamAlias("attachment")
    private class Attachment {
        private String type;
        private Long id;
        private String name;

        @XStreamAlias("web-url")
        private String webUrl;

        private Image image;

        @XStreamAlias("file")
        private YammerFile file;

        private Long size;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public YammerFile getFile() {
            return file;
        }

        public void setFile(YammerFile file) {
            this.file = file;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }
    }

    @XStreamAlias("image")
    private class Image {
        private Long size;
        private String url;

        @XStreamAlias("thumbnail-url")
        private String thumbnailUrl;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }
    }

    @XStreamAlias("file")
    private class YammerFile {
        private Long size;
        private String url;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
/*
 <message>
   <id>1102</id>
   <group-id>1201</group-id>
   <direct-to-id>1005</direct-to-id>
   <url>https://www.yammer.com/api/v1/messages/1102</url>
   <web-url>https://www.yammer.com/messages/1102</web-url>
   <replied-to-id>1101</replied-to-id>
   <thread-id>1101</thread-id>
   <body>
     <plain>I love #yammer.</plain>
     <parsed>I love [[tag:1000]].</parsed>
   </body>
   <attachments>
     <attachment>
       <type>image</type>
       <id>1301</id>
       <name>network-topology.png</name>
       <web-url>https://www.yammer.com/images/1301</web-url>
       <image>
         <size>37235</size>
         <url>https://www.yammer.com/api/v1/images/1301</url>
         <thumbnail-url>https://www.yammer.com/api/v1/images/1301/small</thumbnail-url>
       </image>
     </attachment>
     <attachment>
       <type>file</type>
       <id>1302</id>
       <name>sales-data.xls</name>
       <size>29182109</size>
       <web-url>https://www.yammer.com/files/1302</web-url>
       <file>
         <size>37235</size>
         <url>https://www.yammer.com/api/v1/files/1302</url>
       </file>
     </attachment>
   </attachments>
   <message-type>update</message-type>
   <client-type>web</client-type>
   <sender-id>1002</sender-id>
   <sender-type>user</sender-type>
   <created-at>2008-09-12T17:35:43Z</created-at>
 </message>
*/

}