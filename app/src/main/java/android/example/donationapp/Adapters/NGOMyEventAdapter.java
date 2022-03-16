package android.example.donationapp.Adapters;

import android.example.donationapp.Model.EventClass;
import android.example.donationapp.Model.NGOHomeAdapterClass;
import android.example.donationapp.Model.NGOMyEventAdapterClass;
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

public class NGOMyEventAdapter extends RecyclerView.Adapter<NGOMyEventAdapter.MyViewHolder> {

    ArrayList<EventClass> userList;

    public NGOMyEventAdapter(ArrayList<EventClass> userList) {

        this.userList = userList;
    }

    @NonNull
    @Override
    public NGOMyEventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ngo_myevent_adapter_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NGOMyEventAdapter.MyViewHolder holder, int position) {

        String eTitle = userList.get(position).geteTitle();
        String eLocation = userList.get(position).geteAddress();
        String eTime = userList.get(position).geteTime().toString();
        String eDate = userList.get(position).geteDate().toString();

        String eventTime = eTime + " " + eDate;

        holder.title.setText(eTitle);
        holder.location.setText(eLocation);
        holder.time.setText(eventTime);

        Glide.with(holder.eventPic.getContext()).load(userList.get(position).geteImageUrl()).into(holder.eventPic);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView eventPic;
        private TextView title, location, time;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            eventPic = itemView.findViewById(R.id.myEvent_image);
            title = itemView.findViewById(R.id.myEvent_heading);
            location = itemView.findViewById(R.id.myEvent_location);
            time = itemView.findViewById(R.id.myEvent_time);

        }
    }
}
