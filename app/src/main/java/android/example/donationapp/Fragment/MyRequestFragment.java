package android.example.donationapp.Fragment;

import android.content.Intent;
import android.example.donationapp.Activity.AddRequestActivity;
import android.example.donationapp.Activity.LoginActivity;
import android.example.donationapp.Activity.NGOHomeActivity;
import android.example.donationapp.Activity.RequestDetailActivity;
import android.example.donationapp.Adapters.MyRequestActivityAdapter;
import android.example.donationapp.Model.HomeActivityAdapterClass;
import android.example.donationapp.Model.MyRequestActivityAdapterClass;
import android.example.donationapp.Model.RequestClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyRequestFragment extends Fragment {


    ImageView addRequestButton;

    RecyclerView recyclerView;
    MyRequestActivityAdapter myRequestActivityAdapter;

    //RequestClass requestDetails;

    ArrayList<RequestClass> data = new ArrayList<>();



    String image, heading, location, time, id;
    String blood, contact, description, dob, email, gender, name;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("Request").document(firebaseUser.getUid()).collection("random");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myrequest , container ,false);
        recyclerView = view.findViewById(R.id.recyclerView1);
        addRequestButton = view.findViewById(R.id.addRequest);


        /*data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        */

        notebookRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots)
                {
                    image = documentSnapshots.getString("image");
                    heading = documentSnapshots.getString("title");
                    location = documentSnapshots.getString("address");
                    time = "2 mins ago";

                    id = documentSnapshots.getString("random");
                    blood = documentSnapshots.getString("blood");
                    contact = documentSnapshots.getString("contact");
                    description = documentSnapshots.getString("description");
                    dob = documentSnapshots.getString("dob");
                    email = documentSnapshots.getString("email");
                    gender = documentSnapshots.getString("gender");
                    name = documentSnapshots.getString("name");


                    data.add(new RequestClass(id, name, gender, dob, blood, location, contact, email, heading, description, image));
                }
                myRequestActivityAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        myRequestActivityAdapter = new MyRequestActivityAdapter(data, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myRequestActivityAdapter);


        addRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), AddRequestActivity.class);
                startActivity(intent1);
            }
        });






        return view;
    }


    /*public void sendToDetail(View view) {

        Intent intent1 = new Intent(getContext(), RequestDetailActivity.class);
        intent1.putExtra("id", id);
        intent1.putExtra("name", name);
        intent1.putExtra("gender", gender);
        intent1.putExtra("dob", dob);
        intent1.putExtra("blood", blood);
        intent1.putExtra("address", location);
        intent1.putExtra("contact", contact);
        intent1.putExtra("email", email);
        intent1.putExtra("title", heading);
        intent1.putExtra("description", description);
        intent1.putExtra("image", image);

        startActivity(intent1);
    }*/
}
