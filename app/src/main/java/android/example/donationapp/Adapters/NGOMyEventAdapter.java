package android.example.donationapp.Adapters;

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

import java.util.ArrayList;

public class NGOMyEventAdapter extends RecyclerView.Adapter<NGOMyEventAdapter.MyViewHolder> {

    private ArrayList<NGOHomeAdapterClass> userList;

    public NGOMyEventAdapter(ArrayList<NGOMyEventAdapterClass> userList)
    {
        this.userList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView me_pic;
        private TextView me_title, me_location, me_time;

        public MyViewHolder(View view)
        {
            super(view);

            me_pic = view.findViewById(R.id.myEvent_image);
            me_time = view.findViewById(R.id.myEvent_time);
            me_location = view.findViewById(R.id.myEvent_location);
            me_title = view.findViewById(R.id.myEvent_heading);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ngo_myevent_adapter_layout, parent, false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int myeventpic = userList.get(position).getImageResourceID();
        String myeventHeading = userList.get(position).getHeading();
        String myeventLocation = userList.get(position).getLocation();
        String myeventTime = userList.get(position).getTime();

        holder.me_pic.setImageResource(myeventpic);
        holder.me_title.setText(myeventHeading);
        holder.me_location.setText(myeventLocation);
        holder.me_time.setText(myeventTime);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
