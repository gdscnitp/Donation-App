package android.example.donationapp.Fragment;

import static android.example.donationapp.R.*;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.example.donationapp.Adapters.HomeActivityAdapter;
import android.example.donationapp.Adapters.MyRequestActivityAdapter;
import android.example.donationapp.Model.HomeActivityAdapterClass;
import android.example.donationapp.Model.MyRequestActivityAdapterClass;
import android.example.donationapp.Model.RequestClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    ArrayList<RequestClass> data = new ArrayList<>();

    ArrayList<RequestClass> data1 = new ArrayList<>();

    String image, heading, location, time;
    String id, blood, contact, description, dob, email, gender, name;

    Button request, event;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("All Request");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(layout.fragment_home , container ,false);
        recyclerView = view.findViewById(R.id.recyclerView);

        request = view.findViewById(R.id.RequestTab);
        event = view.findViewById(R.id.EventTab);


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
                homeActivityAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        homeActivityAdapter = new HomeActivityAdapter(data, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeActivityAdapter);





        request.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {


                request.setBackgroundResource(drawable.rectangle_red);
                event.setBackgroundResource(drawable.rectangle33);

                request.setTextColor(color.white);
                event.setTextColor(color.black);



                homeActivityAdapter = new HomeActivityAdapter(data, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(homeActivityAdapter);


            }
        });


        event.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                event.setBackgroundResource(drawable.rectangle_red);
                request.setBackgroundResource(drawable.rectangle33);

                event.setTextColor(color.white);
                request.setTextColor(color.black);

                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
                data1.add(new RequestClass("jaxjkshjsa", "Shreya", "Female", "12 / 08 / 2014", "O+", "Allahabad", "9988797543", "shreya@gmail.com", "jkasjkznxzsilSjnxislNJZxji xzs bewy hsjdskds   iu db di lwei bj xsz ,wibee", "vkeuchenyewmwjiiaxmijw wehmioLZIULWEJ,Lzb bnwxmlz,izuhiQUI WZUILHMLMZL,hziqwh,,zil,wz huzq ZUQ,lliqmLUZ,iuhuu zqw uisnjwjbl xa xkqwiuna  jkzA ZA", "https://images.unsplash.com/photo-1646882172899-8436f0219b71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));


                homeActivityAdapter = new HomeActivityAdapter(data1, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(homeActivityAdapter);

            }
        });






        return view;

    }
}
