package vnext.com.cchat.model;

import java.io.Serializable;

public class Message implements Serializable {
    private User user;
    private String messageText;
    private String messageImage;
    private String messageVideo;
    private String time;
    private static final long serialVersionUID = 3L;
    public Message() {
    }
    public Message(User user,String messageText){
        this.user = user;
        this.messageText = messageText;
    }
    public Message(User user, String messageText,String time){
        this.user = user;
        this.messageText = messageText;
        this.time = time;
    }
    public Message(User user, String messageText, String messageImage, String messageVideo, String time) {
        super();
        this.user = user;
        this.messageText = messageText;
        this.messageImage = messageImage;
        this.messageVideo = messageVideo;
        this.time = time;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(String messageImage) {
        this.messageImage = messageImage;
    }

    public String getMessageVideo() {
        return messageVideo;
    }

    public void setMessageVideo(String messageVideo) {
        this.messageVideo = messageVideo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
