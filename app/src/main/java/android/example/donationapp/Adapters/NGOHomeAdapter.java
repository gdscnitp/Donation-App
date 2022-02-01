package android.example.donationapp.Adapters;

import android.example.donationapp.Model.NGOHomeAdapterClass;
import android.example.donationapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NGOHomeAdapter extends RecyclerView.Adapter<NGOHomeAdapter.MyViewHolder> {

    private ArrayList<NGOHomeAdapterClass> userList;

    public NGOHomeAdapter(ArrayList<NGOHomeAdapterClass> userList)
    {
        this.userList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView e_pic;
        private TextView e_title, e_location, e_time;

        public MyViewHolder(View view)
        {
            super(view);

            e_pic = view.findViewById(R.id.ngo_imageRecycler);
            e_title = view.findViewById(R.id.ngo_headingRecycler);
            e_location = view.findViewById(R.id.ngo_locationRecycler);
            e_time = view.findViewById(R.id.ngo_timeRecycler);

        }
    }

    @NonNull
    @Override
    public NGOHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ngo_home_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int eventpic = userList.get(position).getImageResourceID();
        String eventHeading = userList.get(position).getHeading();
        String eventLocation = userList.get(position).getLocation();
        String eventTime = userList.get(position).getTime();

        holder.e_pic.setImageResource(eventpic);
        holder.e_title.setText(eventHeading);
        holder.e_location.setText(eventLocation);
        holder.e_time.setText(eventTime);
    }




    @Override
    public int getItemCount() {
        return userList.size();
    }
}
