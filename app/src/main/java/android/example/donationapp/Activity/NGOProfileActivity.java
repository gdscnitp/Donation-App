package android.example.donationapp.Activity;

import android.app.TimePickerDialog;
import android.example.donationapp.R;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class NGOProfileActivity extends AppCompatActivity {

    TextView ngoName, ngoDescription, ngoContact, ngoEmail;
    ImageView ngoPic;
    String ndesig;
    String nName, nDescription, nContact, nEmail, nPic;

    FirebaseFirestore firebaseFirestore;
    FirebaseUser currentUser;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile);

        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        collectionReference = firebaseFirestore.collection("UserInformation");

        ngoName = findViewById(R.id.name_ngo);
        ngoDescription = findViewById(R.id.ngo_profile_description);
        ngoContact = findViewById(R.id.call_ngo);
        ngoEmail = findViewById(R.id.email_heading_ngo);
        ngoPic = findViewById(R.id.ngo_profile_pic);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(currentUser != null)
                {
                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                    {
                        ndesig = documentSnapshot.getString("designation");

                        if(ndesig.equalsIgnoreCase("ngo"))
                        {
                            nName = documentSnapshot.getString("name");
                            nDescription = documentSnapshot.getString("description");
                            nContact = documentSnapshot.getString("phoneNo");
                            nEmail = documentSnapshot.getString("email");
                            nPic = documentSnapshot.getString("imageURL");

                            ngoName.setText(nName);
                            ngoDescription.setText(nDescription);
                            ngoContact.setText(nContact);
                            ngoEmail.setText(nEmail);
                            Glide.with(ngoPic.getContext()).load(nPic).placeholder(R.drawable.profile_avatar1).into(ngoPic);
                        }
                    }
                }
                else
                {
                    Toast.makeText(NGOProfileActivity.this, "No NGO Detail Exists", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(NGOProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}