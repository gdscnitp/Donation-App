package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.ChatActivity;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.viewHolder> {

    String name, uid;
    private ArrayList<UserClass> users;
    private Context context;

    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    FirebaseUser currentUser;

    public UsersAdapter(ArrayList<UserClass> chat, Context context)
    {
        users = chat;
        this.context = context;
    }


    @NonNull
    @Override
    public UsersAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.chat_adapter_layout, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.viewHolder holder, int position) {

        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        collectionReference = firebaseFirestore.collection("AcceptedRequest");
//
//        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
//                {
//                    for(Map.Entry<String,String> e1 : )
//                }
//            }
//        });


        String senderID = FirebaseAuth.getInstance().getUid();
        String senderRoom = senderID + users.get(position).getuId();

        FirebaseDatabase.getInstance().getReference()
                .child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String lastmessage = snapshot.child("lastMsg").getValue(String.class);
                            String lastmessagetime = snapshot.child("lastMsgTime").getValue().toString();
                            String lastmessagedate = snapshot.child("lastMsgdate").getValue().toString();

                            holder.lastMessage.setText(lastmessage);
                            holder.lastMessageTime.setText(lastmessagetime);
                            holder.lastMessageDate.setText(lastmessagedate);

                        }
                        else
                        {
                            String temp = "Tap to Reply";
                            holder.lastMessage.setText(temp);
                            holder.lastMessageTime.setText(" ");
                            holder.lastMessageDate.setText(" ");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        holder.userName.setText(users.get(position).getName());
        Glide.with(context).load(users.get(position).getImageURL())
                .placeholder(R.drawable.profile_avatar1)
                .into(holder.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ChatActivity.class);

                intent.putExtra("name", users.get(position).getName());
                intent.putExtra("uid", users.get(position).getuId());
                Log.e("UID is", users.get(position).getuId().toString());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if(users != null){
        return users.size();}
        else
        {
            return 0;
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView userName, lastMessage, lastMessageTime, lastMessageDate;

        public viewHolder(View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.chat_sender_profile_pic);
            userName = itemView.findViewById(R.id.donor_name_chat);
            lastMessage = itemView.findViewById(R.id.donor_mssg_preview);
            lastMessageTime = itemView.findViewById(R.id.chat_time);
            lastMessageDate = itemView.findViewById(R.id.chat_date);

        }
    }
}
