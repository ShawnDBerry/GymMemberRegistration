package com.example.gymmemberregistration.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymmemberregistration.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GymMemberRegistrationActivity extends AppCompatActivity {

    public static String FROM_REGISTRATION_KEY = "from_reg";
    public int REQUEST_CODE = 205;

    @BindView(R.id.gym_member_register_name_editview)
    public EditText registerGymMemberName;

    @BindView(R.id.gym_member_register_plan_editview)
    public EditText registerGymMemberPlan;

    @BindView(R.id.register_member_button)
    public Button registerMemberButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_member_registration);

        ButterKnife.bind(this);

        registerMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (registerGymMemberName.getText().toString().trim().length() > 0 &&
                        registerGymMemberPlan.getText().toString().trim().length() > 0) {

                    String tempMessage = getIntent().getStringExtra(MainActivity.MAIN_KEY);
                    intent = new Intent(GymMemberRegistrationActivity.this, MainActivity.class);
                    String userName = "Member Name: " + registerGymMemberName.getText().toString().trim();
                    String userPlan = "Member Plan: " + registerGymMemberPlan.getText().toString().trim();
                    registerGymMemberName.setText("");
                    registerGymMemberPlan.setText("");
                    intent.putExtra(FROM_REGISTRATION_KEY, tempMessage + "," + userName + "," + userPlan);
                    setResult(AppCompatActivity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(GymMemberRegistrationActivity.this,
                            "Please Fill In The Necessary Fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}
