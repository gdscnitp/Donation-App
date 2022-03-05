package android.example.donationapp.Fragment;

import android.example.donationapp.Adapters.HomeActivityAdapter;
import android.example.donationapp.Model.HomeActivityAdapterClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeActivityAdapter homeActivityAdapter;
    ArrayList<HomeActivityAdapterClass> data = new ArrayList<>();

    String image, heading, location, time;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("All Request");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home , container ,false);
        recyclerView = view.findViewById(R.id.recyclerView);


        /*data.add(new HomeActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new HomeActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
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

                    data.add(new HomeActivityAdapterClass(image, heading, location, time));
                }
                homeActivityAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        homeActivityAdapter = new HomeActivityAdapter(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeActivityAdapter);




        return view;
    }
}
