package android.example.donationapp.Activity;

import android.content.Intent;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.R;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class NGOProfileActivity extends AppCompatActivity {
    ImageView profilePic;
    TextView name, description , contactNo, email;
    Button editButton ;

    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile);

        profilePic = (ImageView) findViewById(R.id.profile_pic);
        name = (TextView) findViewById(R.id.name_ngo);
        description = (TextView) findViewById(R.id.ngo_profile_description);
        contactNo = (TextView) findViewById(R.id.call_ngo);
        email = (TextView) findViewById(R.id.email_heading_ngo);

        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        editButton = findViewById(R.id.ngo_detail_edit_button);

        documentReference = firebaseFirestore.collection("UserInformation").document(currentUser.getUid());

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                UserClass userClass = documentSnapshot.toObject(UserClass.class);

                if(userClass != null){

                    name.setText(userClass.getName());
                    description.setText(userClass.getDescription());
                    contactNo.setText(userClass.getPhoneNo());
                    email.setText(userClass.getEmail());
                    if (userClass.getImageURL() != null && !userClass.getImageURL().equalsIgnoreCase("null")) {
                        Glide.with(NGOProfileActivity.this).load(userClass.getImageURL()).placeholder(R.drawable.profile_avatar).into(profilePic);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        contactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", name.getText().toString(), null));
                startActivity(phoneIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO ,Uri.fromParts("mailto",email.getText().toString(),null));
                startActivity(Intent.createChooser(emailIntent,"Send email"));
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileActivity = new Intent(NGOProfileActivity.this,NGOEditActivity.class);
                startActivity(editProfileActivity);
            }
        });



    }
}