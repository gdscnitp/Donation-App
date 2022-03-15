package android.example.donationapp.Model;

public class UserClass {

    String name , dob ,gender ,phoneNo,email,address,weight,donor , uId , imageURL, description , designation;

    public UserClass() {
    }



    public UserClass(String name, String dob, String gender, String phoneNo, String email, String address, String weight, String donor, String sdesignation, String uId, String imageURL) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.email = email;
        this.address = address;
        this.weight = weight;
        this.donor = donor;
        this.uId = uId;
        this.imageURL = imageURL;
        this.designation = sdesignation;
    }

    public UserClass(String name, String description, String address, String phoneNo, String email, String designation, String uId, String imageURL) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.address = address;
        this.uId = uId;
        this.imageURL = imageURL;
        this.description = description;
        this.designation =designation;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
