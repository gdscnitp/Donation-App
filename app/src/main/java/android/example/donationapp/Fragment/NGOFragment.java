package android.example.donationapp.Fragment;

import android.example.donationapp.Adapters.NGOAdapter;
import android.example.donationapp.Model.NGOClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NGOFragment extends Fragment {

    RecyclerView recyclerView;
    String imageId, desig, ngoName, ngoAddress;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("UserInformation");
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//    private DocumentReference documentReference = firebaseFirestore.collection("UserInformation").document(currentUser.getUid());


    ArrayList<NGOClass> user = new ArrayList<>();
    NGOAdapter ngoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_ngo , container ,false);
        recyclerView = view.findViewById(R.id.ngo_list_recylcer);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    if (currentUser != null)
                    {
                        desig = documentSnapshot.getString("designation");
                        if(desig.equalsIgnoreCase("ngo"))
                        {
                            Log.e("NGOname", desig.toString());
                            imageId = documentSnapshot.getString("imageURL");
                            ngoName = documentSnapshot.getString("name");
                            ngoAddress = documentSnapshot.getString("address");

                            user.add(new NGOClass(imageId, ngoName, ngoAddress));

                        }
                    }
                }

                ngoAdapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        ngoAdapter = new NGOAdapter(user,getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ngoAdapter);




        return view;
    }
}
