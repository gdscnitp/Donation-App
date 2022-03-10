package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.RequestDetailActivity;
import android.example.donationapp.Model.HomeActivityAdapterClass;
import android.example.donationapp.Model.RequestClass;
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

    private Context context;
    private List<RequestClass> userList;

    public HomeActivityAdapter (List<RequestClass>userList, Context context)
    {
        this.context = context;
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


        Glide.with(holder.rimage.getContext()).load(userList.get(position).getImage()).into(holder.rimage);
        holder.rheading.setText(userList.get(position).getTitle());
        holder.rlocation.setText(userList.get(position).getAddress());
        holder.rtime.setText("10 mins ago");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context, RequestDetailActivity.class);
                intent1.putExtra("id", userList.get(position).getRandom());
                intent1.putExtra("name", userList.get(position).getName());
                intent1.putExtra("gender", userList.get(position).getGender());
                intent1.putExtra("dob", userList.get(position).getDob());
                intent1.putExtra("blood", userList.get(position).getBlood());
                intent1.putExtra("address", userList.get(position).getAddress());
                intent1.putExtra("contact", userList.get(position).getContact());
                intent1.putExtra("email", userList.get(position).getEmail());
                intent1.putExtra("title", userList.get(position).getTitle());
                intent1.putExtra("description", userList.get(position).getDescription());
                intent1.putExtra("image", userList.get(position).getImage());

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
