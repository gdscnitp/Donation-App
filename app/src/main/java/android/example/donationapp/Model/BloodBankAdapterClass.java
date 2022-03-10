package android.example.donationapp.Model;

import android.widget.ImageView;

public class BloodBankAdapterClass {
    String name , address;
    String imageResourceId;

    public BloodBankAdapterClass(String name, String address, String imageResourceId) {
        this.name = name;
        this.address = address;
        this.imageResourceId = imageResourceId;
    }

    public BloodBankAdapterClass() {
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

    public String getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(String imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
