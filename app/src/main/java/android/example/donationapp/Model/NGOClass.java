package android.example.donationapp.Model;

public class NGOClass {

    String imageID;
    String ngoName, ngoAddress;

    public NGOClass()
    {

    }

    public NGOClass(String imageID, String ngoName, String ngoAddress)
    {
        this.imageID = imageID;
        this.ngoAddress = ngoAddress;
        this.ngoName = ngoName;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public String getNgoAddress() {
        return ngoAddress;
    }

    public void setNgoAddress(String ngoAddress) {
        this.ngoAddress = ngoAddress;
    }
}
