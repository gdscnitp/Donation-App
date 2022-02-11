package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.example.donationapp.R;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileUserActivity extends AppCompatActivity {

    ImageView backbuton, profileImage, editIcon;
    TextView userName, userGender, userAge, userContact, userMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        backbuton = findViewById(R.id.back_button_profileuser);
        profileImage = findViewById(R.id.user_profile_pic);
        editIcon = findViewById(R.id.edit_icon_userProfile);
        userName = findViewById(R.id.name_userprofile);
        userGender = findViewById(R.id.gender_userprofile);
        userAge = findViewById(R.id.age_userprofile);
        userContact = findViewById(R.id.contact_userprofile);
        userMail = findViewById(R.id.email_userprofile);



    }
}