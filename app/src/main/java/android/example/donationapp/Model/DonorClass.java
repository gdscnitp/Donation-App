package android.example.donationapp.Model;

public class DonorClass {

    String imageResource, donorName, donorAddress, donorPhone, donorMail, donorStatus;

    public DonorClass()
    {

    }

    public DonorClass(String imageResource, String donorName, String donorAddress, String donorPhone, String donorMail)
    {
        this.donorAddress = donorAddress;
        this.donorMail = donorMail;
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.imageResource = imageResource;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorAddress() {
        return donorAddress;
    }

    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonorMail() {
        return donorMail;
    }

    public void setDonorMail(String donorMail) {
        this.donorMail = donorMail;
    }

    public String getDonorStatus() {
        return donorStatus;
    }

    public void setDonorStatus(String donorStatus) {
        this.donorStatus = donorStatus;
    }
}
