package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.EventDetailActivity;
import android.example.donationapp.Activity.NGOEventDetail;
import android.example.donationapp.Model.EventClass;
import android.example.donationapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    public ArrayList<EventClass> userlist;
    private Context context;


    public EventAdapter(ArrayList<EventClass> userlist, Context context)
    {
        this.userlist = userlist;
        this.context = context;

    }

    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ngo_home_adapter_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyViewHolder holder, int position) {

        String eTitle = userlist.get(position).geteTitle();
        String eLocation = userlist.get(position).geteAddress();
        String eTime = userlist.get(position).geteTime().toString();
        String eDate = userlist.get(position).geteDate().toString();

        String eventTime = eTime + " " + eDate;

        holder.title.setText(eTitle);
        holder.location.setText(eLocation);
        holder.time.setText(eventTime);

        Glide.with(holder.eventPic.getContext()).load(userlist.get(position).geteImageUrl()).into(holder.eventPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("eventPic",userlist.get(position).geteImageUrl());
                intent.putExtra("eventName", userlist.get(position).geteTitle());
                intent.putExtra("eventPhone", userlist.get(position).geteContact());
                intent.putExtra("eventMail", userlist.get(position).geteEmail());
                intent.putExtra("eventTime", userlist.get(position).geteTime());
                intent.putExtra("eventDescription",userlist.get(position).geteDescription());
                intent.putExtra("eventAddress",userlist.get(position).geteAddress());
                intent.putExtra("ngoName",userlist.get(position).getNgoName());
                intent.putExtra("ngoDate",userlist.get(position).geteDate());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView eventPic;
        private TextView title, location, time;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            eventPic = itemView.findViewById(R.id.ngo_imageRecycler);
            title = itemView.findViewById(R.id.ngo_headingRecycler);
            location = itemView.findViewById(R.id.ngo_locationRecycler);
            time = itemView.findViewById(R.id.ngo_timeRecycler);


        }
    }

}
