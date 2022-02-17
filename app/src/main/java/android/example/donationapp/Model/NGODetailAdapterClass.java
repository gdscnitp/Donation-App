package android.example.donationapp.Model;

public class NGODetailAdapterClass {

    private int imageResuorceId;
    private String name , address ,time;

    public NGODetailAdapterClass(int imageResuorceId, String name, String address, String time) {
        this.imageResuorceId = imageResuorceId;
        this.name = name;
        this.address = address;
        this.time = time;
    }

    public int getImageResuorceId() {
        return imageResuorceId;
    }

    public void setImageResuorceId(int imageResuorceId) {
        this.imageResuorceId = imageResuorceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
