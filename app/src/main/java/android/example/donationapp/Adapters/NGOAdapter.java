package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.NGODetailActivity;
import android.example.donationapp.Model.NGOClass;
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

public class NGOAdapter extends RecyclerView.Adapter<NGOAdapter.MyViewHolder>
{

    private ArrayList<NGOClass> userList;
    Context context;

    public NGOAdapter(ArrayList<NGOClass> userList, Context context)
    {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.ngo_list_adapter_layout, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(holder.ngoPic.getContext()).load(userList.get(position)).placeholder(R.drawable.ngo_list_image1).into(holder.ngoPic);
        holder.ngoName.setText(userList.get(position).getNgoName());
        holder.ngoAddress.setText(userList.get(position).getNgoAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, NGODetailActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ngoPic;
        TextView ngoName, ngoAddress;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            ngoPic = itemView.findViewById(R.id.ngo_list_adapter_profile_pic);
            ngoName = itemView.findViewById(R.id.ngo_detail_adapter_name);
            ngoAddress = itemView.findViewById(R.id.ngo_list_adapter_address);

        }
    }
}