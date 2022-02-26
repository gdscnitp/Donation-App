package android.example.donationapp.Adapters;

import android.content.Context;
import android.example.donationapp.Model.BloodBankAdapterClass;
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

import javax.microedition.khronos.opengles.GL;

public class BloodBankAdapter extends RecyclerView.Adapter<BloodBankAdapter.viewHolder> {

    private List<BloodBankAdapterClass> bloodBankAdapterClasses;
    Context context;

    public BloodBankAdapter() {
    }

    public BloodBankAdapter(List<BloodBankAdapterClass> bloodBankAdapterClasses , Context context) {
        this.bloodBankAdapterClasses = bloodBankAdapterClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public BloodBankAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_bloodbank_adapter_layout,parent,false);


        return new BloodBankAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.bloodBankName.setText(bloodBankAdapterClasses.get(position).getName());
        holder.bloodBankAddress.setText(bloodBankAdapterClasses.get(position).getAddress());
        Glide.with(context).load(bloodBankAdapterClasses.get(position).getImageResourceId()).placeholder(R.drawable.blood_bank_icon).into(holder.bloodBankIcon);


    }


    @Override
    public int getItemCount() {

        return bloodBankAdapterClasses.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView bloodBankIcon;
        TextView bloodBankName , bloodBankAddress;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            bloodBankIcon = itemView.findViewById(R.id.blood_bank_icon);
            bloodBankName = itemView.findViewById(R.id.blood_bank_name);
            bloodBankAddress = itemView.findViewById(R.id.blood_bank_address);

        }
    }

}
