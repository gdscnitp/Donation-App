package android.example.donationapp.Model;

public class MessageClass {

    String messageID, mesage, senderId, name, messageTime, messageDate;

    public MessageClass()
    {

    }

    public MessageClass(String message, String senderId, String messageTime, String messageDate) {
        this.mesage = message;
        this.senderId = senderId;
        this.messageTime = messageTime;
        this.messageDate = messageDate;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return mesage;
    }

    public void setMessage(String message) {
        this.mesage = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
}
