package android.example.donationapp.Fragment;

import android.content.Intent;
import android.example.donationapp.Activity.AddEventsActivity;
import android.example.donationapp.Adapters.EventAdapter;
import android.example.donationapp.Adapters.NGOHomeAdapter;
import android.example.donationapp.Model.EventClass;
import android.example.donationapp.Model.HomeActivityAdapterClass;
import android.example.donationapp.Model.NGOHomeAdapterClass;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.donationapp.R;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NGOHomeFragment extends Fragment {

    RecyclerView recyclerView;
//    ArrayList<NGOHomeAdapterClass> userList;
    FloatingActionButton addButton;

    EventAdapter eventAdapter;
    EventClass eventClass;
    ArrayList<EventClass> userList;

    String eventImage, eventDescription, eventTitle, eventLocation, etime, edate, eventTime;
    String econtact = "null", eEmail = "null";
    String UID;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference collectionReference = db.collection("Events").document(firebaseUser.getUid()).collection("random");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ngo_home, container, false);

        recyclerView = view.findViewById(R.id.ngo_recyclerView);
        addButton = view.findViewById(R.id.add_activity_button);
        userList = new ArrayList<EventClass>();
        eventAdapter = new EventAdapter(userList, getContext());

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    eventImage = documentSnapshot.getString("eImageUrl");
                    eventTitle = documentSnapshot.getString("eTitle");
                    eventDescription = documentSnapshot.getString("eDescription");
                    eventLocation = documentSnapshot.getString("eAddress");
                    etime = documentSnapshot.getString("eTime");
                    edate = documentSnapshot.getString("eDate");
                    UID = documentSnapshot.getString("uid");
                    String ngoName = documentSnapshot.getString("ngoName");

                    userList.add(new EventClass(eventTitle, edate, etime, eventDescription, eventImage, eventLocation, econtact, eEmail, UID, ngoName));
                }
                eventAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
//        userList.add(new NGOHomeAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NGOHomeFragment.this.getActivity(), AddEventsActivity.class);
                startActivity(intent);

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventAdapter);

        return view;
    }
}