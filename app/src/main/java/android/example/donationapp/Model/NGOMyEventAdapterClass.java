package android.example.donationapp.Model;

public class NGOMyEventAdapterClass {

    private int imageID;
    private String heading, location, time;

    public NGOMyEventAdapterClass(int image, String sHeading, String sLocation, String sTime)
    {
        this.imageID = image;
        this.heading = sHeading;
        this.location = sLocation;
        this.time = sTime;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
