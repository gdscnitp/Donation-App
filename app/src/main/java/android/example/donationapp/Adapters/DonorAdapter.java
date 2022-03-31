package android.example.donationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.example.donationapp.Activity.DonorActivity;
import android.example.donationapp.Model.DonorClass;
import android.example.donationapp.R;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.MyViewHolder> {

    ArrayList<DonorClass> userList;
    Context context;

    public DonorAdapter() {
    }

    public DonorAdapter(ArrayList<DonorClass> userList, Context context)
    {
        this.userList = userList;
        this.context = context;

    }
    @NonNull
    @Override
    public DonorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_adapter_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DonorAdapter.MyViewHolder holder, int position) {

        String imageid = userList.get(position).getImageResource();
        String donorName = userList.get(position).getDonorName();
        String donorMail = userList.get(position).getDonorMail();
        String donorPhone = userList.get(position).getDonorPhone();
        String donorAddress = userList.get(position).getDonorAddress();

        Glide.with(holder.dpic.getContext()).load(imageid).placeholder(R.drawable.profile_avatar1).into(holder.dpic);

        holder.dName.setText(donorName);
        holder.dAddress.setText(donorAddress);

        holder.dPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",donorPhone,null));

                try {
                    context.startActivity(callIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(view.getContext(), "Application not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.dMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:" + donorMail));

                try {
                    context.startActivity(mailIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(view.getContext(), "Application not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView dpic;
        TextView dName, dAddress;
        ImageView dPhone , dMail;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            dpic = itemView.findViewById(R.id.donor_profile_pic);
            dName = itemView.findViewById(R.id.donor_name);
            dAddress = itemView.findViewById(R.id.donor_address);
            dPhone = itemView.findViewById(R.id.donor_call);
            dMail = itemView.findViewById(R.id.donor_email);

        }
    }
}
