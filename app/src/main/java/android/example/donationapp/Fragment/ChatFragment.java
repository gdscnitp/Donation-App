package android.example.donationapp.Fragment;

import android.example.donationapp.Adapters.ChatAdapter;
import android.example.donationapp.Model.ChatClass;
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
import java.util.concurrent.RecursiveAction;

public class ChatFragment extends Fragment {

    private ArrayList<ChatClass> usersList;
    RecyclerView recyclerView;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_chat , container ,false);

        recyclerView = view.findViewById(R.id.chat_list_recycler);

        usersList = new ArrayList<ChatClass>();

        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));
        usersList.add(new ChatClass(R.drawable.profile_avatar1,"Sujit Kumar Das", "I want to donate.", "26/01/2020", "7:20 pm"));

        ChatAdapter adapter = new ChatAdapter(usersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        
        return view;
    }
}
