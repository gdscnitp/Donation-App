package android.example.donationapp.Adapters;

import android.example.donationapp.Model.ChatClass;
import android.example.donationapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    public ArrayList<ChatClass> userslist;

    public ChatAdapter(ArrayList<ChatClass> userslist)
    {
        this.userslist = userslist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView profilepic;
        private TextView sender_name, sender_message, message_time, message_date;

        public MyViewHolder(View view)
        {
            super(view);

            profilepic =view.findViewById(R.id.chat_sender_profile_pic);
            sender_name = view.findViewById(R.id.donor_name_chat);
            sender_message = view.findViewById(R.id.donor_mssg_preview);
            message_date = view.findViewById(R.id.chat_date);
            message_time = view.findViewById(R.id.chat_time);
        }
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {

        int pic = userslist.get(position).getProfilepic();
        String senderName = userslist.get(position).getSenderName();
        String senderMessage = userslist.get(position).getMessage();
        String messageDate = userslist.get(position).getMessageDate();
        String messageTime = userslist.get(position).getMessageTime();

        holder.profilepic.setImageResource(pic);
        holder.sender_name.setText(senderName);
        holder.sender_message.setText(senderMessage);
        holder.message_date.setText(messageDate);
        holder.message_time.setText(messageTime);

    }

    @Override
    public int getItemCount() {
        return userslist.size();
    }
}
