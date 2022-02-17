package android.example.donationapp.Adapters;

import android.example.donationapp.Model.NGODetailAdapterClass;
import android.example.donationapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NGODetailAdapter extends RecyclerView.Adapter<NGODetailAdapter.viewHolder> {

    private List<NGODetailAdapterClass> ngoDetailAdapterClassList;

    public NGODetailAdapter(List<NGODetailAdapterClass> ngoDetailAdapterClassList) {
        this.ngoDetailAdapterClassList = ngoDetailAdapterClassList;
    }

    @NonNull
    @Override
    public NGODetailAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ngo_detail_adapter_layout,parent,false);
        return new NGODetailAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.timeTV.setText(ngoDetailAdapterClassList.get(position).getTime());
        holder.addressTV.setText(ngoDetailAdapterClassList.get(position).getAddress());
        holder.nameTV.setText(ngoDetailAdapterClassList.get(position).getName());
        holder.profileIV.setImageResource(ngoDetailAdapterClassList.get(position).getImageResuorceId());
    }

    @Override
    public int getItemCount() {
        return ngoDetailAdapterClassList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView profileIV;
        TextView nameTV , addressTV , timeTV;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profileIV = itemView.findViewById(R.id.ngo_detail_adapter_profile_pic);
            nameTV = itemView.findViewById(R.id.ngo_detail_adapter_name);
            addressTV = itemView.findViewById(R.id.ngo_detail_adapter_address);
            timeTV = itemView.findViewById(R.id.ngo_detail_adapter_time);

        }
    }

}
