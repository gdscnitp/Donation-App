package android.example.donationapp.Fragment;

import android.example.donationapp.Adapters.NGOMyEventAdapter;
import android.example.donationapp.Model.NGOHomeAdapterClass;
import android.example.donationapp.Model.NGOMyEventAdapterClass;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.donationapp.R;

import java.util.ArrayList;


public class NGOMyEventsFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NGOMyEventAdapterClass> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_ngo_my_events, container, false);

        recyclerView = view.findViewById(R.id.ngo_myEvent_recycler);
        userList = new ArrayList<NGOMyEventAdapterClass>();

        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        userList.add(new NGOMyEventAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));

        NGOMyEventAdapter ngoMyEventAdapter = new NGOMyEventAdapter(userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ngoMyEventAdapter);


        return view;
    }
}