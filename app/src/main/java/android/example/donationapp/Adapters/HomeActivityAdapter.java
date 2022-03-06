package android.example.donationapp.Adapters;

import android.example.donationapp.Model.HomeActivityAdapterClass;
import android.example.donationapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.viewHolder> {

    private List<HomeActivityAdapterClass> userList;

    public HomeActivityAdapter (List<HomeActivityAdapterClass>userList)
    {
        this.userList = userList;
    }


    @NonNull
    @Override
    public HomeActivityAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_home_request_event_adapter_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeActivityAdapter.viewHolder holder, int position) {


        Glide.with(holder.rimage.getContext()).load(userList.get(position).getImageResourceID()).into(holder.rimage);
        holder.rheading.setText(userList.get(position).getHeading());
        holder.rlocation.setText(userList.get(position).getLocation());
        holder.rtime.setText(userList.get(position).getTime());


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
