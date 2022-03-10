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


    @NonNull
    @Override
    public NGOMyEventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NGOMyEventAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

        }
    }
}
