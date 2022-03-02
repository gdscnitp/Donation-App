package android.example.donationapp.Fragment;

import android.example.donationapp.Adapters.ChatAdapter;
import android.example.donationapp.Adapters.UsersAdapter;
import android.example.donationapp.Model.ChatClass;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.Model.UsersAdapterClass;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class ChatFragment extends Fragment {

    String sName, sDob, sPhone, sEmail, sPassword, sAddress, sWeight, sdesignation = "User", sImageUrl = "null", sDonor = "null", sGender, uID;
    UsersAdapterClass usersAdapterClass;
    UsersAdapter usersAdapter;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference;
    FirebaseUser currentUser;

    ArrayList<UserClass> usersList;
    RecyclerView recyclerView;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_chat , container ,false);

        usersList = new ArrayList<UserClass>();
        collectionReference = firebaseFirestore.collection("UserInformation");
        usersAdapter = new UsersAdapter(usersList, getContext());

        recyclerView = view.findViewById(R.id.chat_list_recycler);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                usersList.clear();

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {

                    sName = documentSnapshot.getString("name");
                    sDob = documentSnapshot.getString("dob");
                    sPhone = documentSnapshot.getString("phoneNo");
                    sEmail = documentSnapshot.getString("email");
//                    sPassword = documentSnapshot.getString("sPassword");
                    sAddress = documentSnapshot.getString("address");
                    sWeight = documentSnapshot.getString("weight");
                    sdesignation = documentSnapshot.getString("designation");
                    sImageUrl = documentSnapshot.getString("imageURL");
                    sGender = documentSnapshot.getString("gender");
                    uID = documentSnapshot.getString("uId");
                    sDonor = documentSnapshot.getString("donor");
                    Log.e("Name", sName);
                    Log.e("UID", uID);

                    if(currentUser != null)
                    {
                        if(uID.equalsIgnoreCase(currentUser.getUid()))
                        {
                            continue;
                        }
                    }

                    usersList.add(new UserClass(sName, sDob, sGender, sPhone, sEmail, sAddress, sWeight, sDonor, sdesignation, uID, sImageUrl));
//    public UserClass(String name, String dob, String gender, String phoneNo, String email, String address, String weight, String donor, String sdesignation, String uId, String imageURL) {

                }

                usersAdapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
//        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(usersAdapter);
        
        return view;
    }
}
