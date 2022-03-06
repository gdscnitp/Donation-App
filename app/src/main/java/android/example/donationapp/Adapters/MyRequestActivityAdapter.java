package android.example.donationapp.Adapters;

import android.example.donationapp.Model.MyRequestActivityAdapterClass;
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

public class MyRequestActivityAdapter extends RecyclerView.Adapter<MyRequestActivityAdapter.viewHolder> {

    private List<MyRequestActivityAdapterClass> UserList;

    public MyRequestActivityAdapter (List<MyRequestActivityAdapterClass> userList)
    {
        this.UserList = userList;
    }

    @NonNull
    @Override
    public MyRequestActivityAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_my_requests_adapter_layout, parent, false);
        return new MyRequestActivityAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRequestActivityAdapter.viewHolder holder, int position) {

        Glide.with(holder.rimage1.getContext()).load(UserList.get(position).getImageResourceID()).into(holder.rimage1);
        holder.rheading1.setText(UserList.get(position).getHeading());
        holder.rlocation1.setText(UserList.get(position).getLocation());
        holder.rtime1.setText(UserList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView rimage1;
        TextView rheading1, rlocation1, rtime1;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            rimage1 = itemView.findViewById(R.id.imageRecycler1);
            rheading1 = itemView.findViewById(R.id.headingRecycler1);
            rlocation1 = itemView.findViewById(R.id.locationRecycler1);
            rtime1 = itemView.findViewById(R.id.timeRecycler1);

        }
    }


}
