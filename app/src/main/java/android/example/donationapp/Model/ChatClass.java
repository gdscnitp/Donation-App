package android.example.donationapp.Model;

public class ChatClass {

    private String senderName, message, messageDate, messageTime;
    private int profilepic;

    public ChatClass(int profilepic, String senderName, String message, String messageDate, String messageTime)
    {
        this.message = message;
        this.messageDate = messageDate;
        this.messageTime = messageTime;
        this.profilepic = profilepic;
        this.senderName = senderName;
    }

    public int getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(int profilepic) {
        this.profilepic = profilepic;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
