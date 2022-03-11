package android.example.donationapp.Model;

public class EventClass {

    String eTitle, eDescription, eTime, eDate, eAddress, eContact, eEmail;
    String eImageUrl;
    String UID;

    public EventClass()
    {

    }

//    public int geteImage() {
//        return eImage;
//    }
//
//    public void seteImage(int eImage) {
//        this.eImage = eImage;
//    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public EventClass(String eTitle, String eDate, String eTime, String eDescription, String geteImageUrl, String eAddress, String eContact, String eEmail, String UID)
    {
        this.eTitle = eTitle;
        this.eDescription = eDescription;
        this.eDate = eDate;
        this.eAddress = eAddress;
        this.eEmail = eEmail;
        this.eTime = eTime;
        this.eImageUrl = geteImageUrl;
        this.eContact = eContact;
        this.UID = UID;
    }

    public String geteTitle() {
        return eTitle;
    }

    public void seteTitle(String eTitle) {
        this.eTitle = eTitle;
    }

    public String geteDescription() {
        return eDescription;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String geteImageUrl() {
        return eImageUrl;
    }

    public void seteImageUrl(String eImageUrl) {
        this.eImageUrl = eImageUrl;
    }

    public String geteAddress() {
        return eAddress;
    }

    public void seteAddress(String eAddress) {
        this.eAddress = eAddress;
    }

    public String geteContact() {
        return eContact;
    }

    public void seteContact(String eContact) {
        this.eContact = eContact;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }
}
