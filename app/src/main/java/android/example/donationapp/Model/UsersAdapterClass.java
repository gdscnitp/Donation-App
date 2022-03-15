package android.example.donationapp.Model;

public class UsersAdapterClass {

    private String sName, sLastMessage, sDate, sTime;
    int sImageResourceId;

    public UsersAdapterClass()
    {

    }

    public UsersAdapterClass(String sName, String slastMessage, String sdate, String stime, int imageResource)
    {
        this.sDate=sdate;
        this.sImageResourceId=imageResource;
        this.sTime=stime;
        this.sLastMessage=slastMessage;
        this.sName=sName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsLastMessage() {
        return sLastMessage;
    }

    public void setsLastMessage(String sLastMessage) {
        this.sLastMessage = sLastMessage;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public int getsImageResourceId() {
        return sImageResourceId;
    }

    public void setsImageResourceId(int sImageResourceId) {
        this.sImageResourceId = sImageResourceId;
    }
}
