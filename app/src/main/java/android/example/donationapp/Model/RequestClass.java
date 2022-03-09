package android.example.donationapp.Model;

public class RequestClass {

    String random, name, gender, dob, blood, address, contact, email, title, description, image;

    public RequestClass() {}




    public RequestClass(String srandom, String sname, String sgender, String sdob, String sblood, String saddress, String scontact, String semail, String stitle, String sdescription, String image) {
        this.random = srandom;
        this.name = sname;
        this.gender = sgender;
        this.dob = sdob;
        this.blood = sblood;
        this.address = saddress;
        this.contact = scontact;
        this.email = semail;
        this.title = stitle;
        this.description = sdescription;
        this.image = image;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
