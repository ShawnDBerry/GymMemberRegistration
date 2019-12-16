package com.example.gymmemberregistration.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmemberregistration.R;
import com.example.gymmemberregistration.model.GymMember;

import java.util.List;

public class GymMemberRVAdapter extends RecyclerView.Adapter<GymMemberRVAdapter.CustomGymMemberViewHolder> {
    private List<GymMember> gymMembers;
    private GymMemberDelegate gymMemberDelegate;


    public interface GymMemberDelegate {
        void gymMemberSelected(GymMember selectedGymMember);
    }

    public GymMemberRVAdapter(List<GymMember> gymMembers, GymMemberDelegate gymMemberDelegate) {
        this.gymMembers = gymMembers;
        this.gymMemberDelegate = gymMemberDelegate;

    }

    @NonNull
    @Override
    public CustomGymMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gym_member_item_layout, parent, false);

        return new CustomGymMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomGymMemberViewHolder holder, final int position) {
        //holder.gymMemberIdTextView.setText(gymMembers.get(position).getGymMemberId());
        holder.gymMemberNameTextView.setText(gymMembers.get(position).getGymMemberName());
        holder.gymMemberPlanTextView.setText(gymMembers.get(position).getGymMemberPlan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gymMemberDelegate.gymMemberSelected(gymMembers.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return gymMembers.size();
    }


    class CustomGymMemberViewHolder extends RecyclerView.ViewHolder {

        public TextView gymMemberIdTextView;
        public TextView gymMemberNameTextView;
        public TextView gymMemberPlanTextView;


        public CustomGymMemberViewHolder(@NonNull View itemView) {
            super(itemView);
            //gymMemberIdTextView = itemView.findViewById(R.id.gym_member_id_textview);
            gymMemberNameTextView = itemView.findViewById(R.id.gym_member_name_textview);
            gymMemberPlanTextView = itemView.findViewById(R.id.gym_member_plan_textview);
        }
    }

}
