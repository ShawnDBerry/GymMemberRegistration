package com.example.gymmemberregistration.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymmemberregistration.R;
import com.example.gymmemberregistration.model.GymMember;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GymMembersDetailedInfoActivity extends AppCompatActivity {

    @BindView(R.id.detailed_gym_member_id_textview)
    public TextView gymMemberDetailedId;

    @BindView(R.id.detailed_gym_member_name_textview)
    public TextView gymMemberDetailedName;

    @BindView(R.id.detailed_gym_member_plan_textview)
    public TextView gymMemberDetailedPlan;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_members_detailed_info);

        ButterKnife.bind(this);

        String gymMemberId = "Gym Member ID: " + ((GymMember)getIntent().getParcelableExtra("my_parcel")).getGymMemberId();
        String gymMemberName = ((GymMember)getIntent().getParcelableExtra("my_parcel")).getGymMemberName();
        String gymMemberPlan = ((GymMember)getIntent().getParcelableExtra("my_parcel")).getGymMemberPlan();

        gymMemberDetailedId.setText(gymMemberId);
        gymMemberDetailedName.setText(gymMemberName);
        gymMemberDetailedPlan.setText(gymMemberPlan);


    }
}
