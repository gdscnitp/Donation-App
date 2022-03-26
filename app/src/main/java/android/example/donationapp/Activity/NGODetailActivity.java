package android.example.donationapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.example.donationapp.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class NGODetailActivity extends AppCompatActivity {

    ImageView backButton;
    ImageView phoneCall, email, share;
    ImageView ngoPic;
    TextView more;
    TextView NGOname, NGODescription;

    String desig, nName, nDescription, nContact, nEmail;

    FirebaseFirestore firebaseFirestore;
    FirebaseUser currentUser;
    CollectionReference collectionReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodetail);

        backButton = findViewById(R.id.ngo_detail_backarrow);
        phoneCall = findViewById(R.id.phone_vector_ngodetail);
        email = findViewById(R.id.mail_vector_ngodetail);
        share = findViewById(R.id.share_vector_ngodetail);
        ngoPic = findViewById(R.id.ngo_profile_pic);
        more = findViewById(R.id.ngo_detail_more);
        NGOname = findViewById(R.id.name_ngodetail);
        NGODescription = findViewById(R.id.ngo_detail_description_less);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("UserInformation");

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(currentUser != null)
                {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                    {
                        desig = documentSnapshot.getString("designation");
                        Log.e("NGODetail desig", desig.toString());

                        if(desig.equalsIgnoreCase("ngo"))
                        {
                            nName = documentSnapshot.getString("name");
                            nDescription = documentSnapshot.getString("description");
                            nContact = documentSnapshot.getString("phoneNo");
                            nEmail = documentSnapshot.getString("email");
                            String nPic = documentSnapshot.getString("imageURL");

                            NGOname.setText(nName);
                            NGODescription.setText(nDescription);
                            Glide.with(ngoPic.getContext()).load(nPic).into(ngoPic);

                        }
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NGODetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}