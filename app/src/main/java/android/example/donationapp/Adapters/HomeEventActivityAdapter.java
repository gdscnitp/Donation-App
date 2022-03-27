package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.EventDetailActivity;
import android.example.donationapp.Activity.RequestDetailActivity;
import android.example.donationapp.Model.EventClass;
import android.example.donationapp.Model.RequestClass;
import android.example.donationapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeEventActivityAdapter extends RecyclerView.Adapter<HomeEventActivityAdapter.viewHolder>{


    private Context context;
    private List<EventClass> userList;

    public HomeEventActivityAdapter (List<EventClass>userList, Context context)
    {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public HomeEventActivityAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_home_request_event_adapter_layout, parent, false);
        return new HomeEventActivityAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeEventActivityAdapter.viewHolder holder, int position) {

        Glide.with(holder.rimage.getContext()).load(userList.get(position).geteImageUrl()).into(holder.rimage);
        holder.rheading.setText(userList.get(position).geteTitle());
        holder.rlocation.setText(userList.get(position).geteAddress());
        holder.rtime.setText("10 mins ago");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context, EventDetailActivity.class);
                intent1.putExtra("eventName", userList.get(position).geteTitle());
                intent1.putExtra("ngoName", userList.get(position).getNgoName());
                intent1.putExtra("eventDescription", userList.get(position).geteDescription());
                intent1.putExtra("ngoDate", userList.get(position).geteDate());
                intent1.putExtra("eventAddress", userList.get(position).geteAddress());
                intent1.putExtra("eventMail", userList.get(position).geteEmail());
                intent1.putExtra("eventTime", userList.get(position).geteTime());
                intent1.putExtra("eventPic", userList.get(position).geteImageUrl());
                intent1.putExtra("eventPhone", userList.get(position).geteContact());
                intent1.putExtra("uid", userList.get(position).getUID());
                intent1.putExtra("generated string", userList.get(position).getGeneratedString());

                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView rimage;
        TextView rheading, rlocation, rtime;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            rimage = itemView.findViewById(R.id.imageRecycler);
            rheading = itemView.findViewById(R.id.headingRecycler);
            rlocation = itemView.findViewById(R.id.locationRecycler);
            rtime = itemView.findViewById(R.id.timeRecycler);

        }
    }
}
