package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.RequestDetailActivity;
import android.example.donationapp.Model.MyRequestActivityAdapterClass;
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

public class MyRequestActivityAdapter extends RecyclerView.Adapter<MyRequestActivityAdapter.viewHolder> {

    private Context context;
    private List<RequestClass> UserList;

    public MyRequestActivityAdapter (List<RequestClass> userList, Context context)
    {
        this.context = context;
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




        Glide.with(holder.rimage1.getContext()).load(UserList.get(position).getImage()).into(holder.rimage1);
        holder.rheading1.setText(UserList.get(position).getTitle());
        holder.rlocation1.setText(UserList.get(position).getAddress());
        holder.rtime1.setText("5mins ago");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context, RequestDetailActivity.class);
                intent1.putExtra("id", UserList.get(position).getRandom());
                intent1.putExtra("name", UserList.get(position).getName());
                intent1.putExtra("gender", UserList.get(position).getGender());
                intent1.putExtra("dob", UserList.get(position).getDob());
                intent1.putExtra("blood", UserList.get(position).getBlood());
                intent1.putExtra("address", UserList.get(position).getAddress());
                intent1.putExtra("contact", UserList.get(position).getContact());
                intent1.putExtra("email", UserList.get(position).getEmail());
                intent1.putExtra("title", UserList.get(position).getTitle());
                intent1.putExtra("description", UserList.get(position).getDescription());
                intent1.putExtra("image", UserList.get(position).getImage());

                context.startActivity(intent1);
            }
        });

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
