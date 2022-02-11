package android.example.donationapp.Fragment;

import android.example.donationapp.Adapters.MyRequestActivityAdapter;
import android.example.donationapp.Model.MyRequestActivityAdapterClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRequestFragment extends Fragment {


    RecyclerView recyclerView;
    MyRequestActivityAdapter myRequestActivityAdapter;
    ArrayList<MyRequestActivityAdapterClass> data = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myrequest , container ,false);
        recyclerView = view.findViewById(R.id.recyclerView1);


        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.vector_signup_background, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));
        data.add(new MyRequestActivityAdapterClass(R.drawable.edit_photo, "Emergency! O+ blood required for child of age 12...", "Paras Hospital, Bailey Road, Patna", "2 mins ago"));




        myRequestActivityAdapter = new MyRequestActivityAdapter(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myRequestActivityAdapter);




        return view;
    }
}
