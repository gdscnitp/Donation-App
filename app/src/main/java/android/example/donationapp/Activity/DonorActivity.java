package android.example.donationapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.donationapp.Adapters.DonorAdapter;
import android.example.donationapp.Model.DonorClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DonorActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String imageid, dName, dAddress;
    String dPhone, dmail;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    FirebaseUser currentUser;

    ArrayList<DonorClass> user;
    DonorAdapter donorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("UserInformation");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        user = new ArrayList<DonorClass>();
        donorAdapter = new DonorAdapter(user, getApplicationContext());
        recyclerView = findViewById(R.id.donor_recycler);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    if(currentUser != null)
                    {
                        String desig = documentSnapshot.getString("designation");
                        if(desig.equalsIgnoreCase("user"));
                        {
                            String donorStatus = documentSnapshot.getString("donor");
                            if(donorStatus.equalsIgnoreCase("true"))
                            {
                                imageid = documentSnapshot.getString("imageURL");
                                dName = documentSnapshot.getString("name");
                                dAddress = documentSnapshot.getString("address");
                                dPhone = documentSnapshot.getString("phoneNo");
                                dmail = documentSnapshot.getString("email");

                                user.add(new DonorClass(imageid, dName, dAddress, dPhone, dmail));

                            }
                        }
                    }
                }

                donorAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DonorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(donorAdapter);


    }
}