package android.example.donationapp.Activity;

import android.content.Intent;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileUserActivity extends AppCompatActivity {

    ImageView backbuton, profileImage, editIcon;
    TextView userName, userGender, userAge, userContact, userMail;
    FirebaseFirestore firebaseFirestore ;
    FirebaseUser currentUser;
    String name, gender , age , email ,contact , imageUrl;
    UserClass userClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        backbuton = findViewById(R.id.back_button_profileuser);
        profileImage = findViewById(R.id.ngo_profile_pic1);
        editIcon = findViewById(R.id.edit_icon_userProfile);
        userName = findViewById(R.id.name_userprofile);
        userGender = findViewById(R.id.gender_userprofile);
        userAge = findViewById(R.id.age_userprofile);
        userContact = findViewById(R.id.contact_userprofile);
        userMail = findViewById(R.id.email_userprofile);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Initializing variable
        Log.e("Outside Current User", "onCreate: " + currentUser.getUid() );

        firebaseFirestore = FirebaseFirestore.getInstance();
        if(currentUser != null) {
            Log.e("Inside Current User", "onCreate: " + currentUser.getUid() );

            firebaseFirestore.collection("UserInformation").document(currentUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Log.e("Inside onSuccess", ""+ currentUser.getUid());
                     userClass = documentSnapshot.toObject(UserClass.class);
                    Log.e("After userclass", ""+ userClass.toString());

                    if(userClass != null) {
                        Log.e("Inside userclass", ""+ currentUser.getUid());

                        name = userClass.getName();
                        gender = userClass.getGender();
                        age = userClass.getDob();
                        contact = userClass.getPhoneNo();
                        email = userClass.getEmail();
                        imageUrl = userClass.getImageURL();

                        userName.setText(name);
                        userGender.setText(gender);
                        userAge.setText(age);
                        userContact.setText(contact);
                        userMail.setText(email);

                        if (imageUrl != null && !imageUrl.equalsIgnoreCase("null")) {
                            Glide.with(ProfileUserActivity.this).load(imageUrl).placeholder(R.drawable.profile_avatar1).into(profileImage);
                        }

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });


        }

        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfile = new Intent(ProfileUserActivity.this, UserEditActivity.class);
                startActivity(editProfile);

            }
        });



    }
}