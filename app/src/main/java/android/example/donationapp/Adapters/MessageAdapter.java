package android.example.donationapp.Adapters;

import android.content.Context;
import android.example.donationapp.Model.MessageClass;
import android.example.donationapp.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> {

    int ITEM_SENT = 1;
    int ITEM_RECEIVE = 2;

    Context context;
    ArrayList<MessageClass> usersList;

    public MessageAdapter(ArrayList<MessageClass> usersList, Context context)
    {
        this.context = context;
        this.usersList = usersList;
        Log.e("Adapter UserList",usersList.toString());
    }

    @NonNull
    @Override
    public MessageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == ITEM_SENT)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_sent_layout, parent, false);
            return new viewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recieve_layout, parent, false);
            return new viewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        String message = usersList.get(position).getMessage();

        Log.e("Message after Retrieve",message );


        if (message.equals("")) {
            holder.messageView.setHeight(0);
            holder.messageView.setWidth(0);
            holder.messageView.setVisibility(View.INVISIBLE);
        } else {
            if (message != null) {
                Log.e("Message of MessageAdapter", message.toString());
//                Log.e()
                holder.messageView.setText(message);
                Log.e("Message Updated", message.toString());
            }
        }
        }



    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView messageView;

        public viewHolder(@NonNull View itemView) {

            super(itemView);

            messageView = (TextView) itemView.findViewById(R.id.senderMessage);

        }


    }

    public int getItemViewType(int position) {
        MessageClass messages = usersList.get(position);

        if (Objects.requireNonNull(FirebaseAuth.getInstance().getUid()).equalsIgnoreCase(messages.getSenderId())) {
            return ITEM_SENT;
        } else {
            return ITEM_RECEIVE;
        }
    }


}
