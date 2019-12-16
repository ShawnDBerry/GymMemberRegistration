package com.example.gymmemberregistration.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymmemberregistration.R;
import com.example.gymmemberregistration.adapter.GymMemberRVAdapter;
import com.example.gymmemberregistration.model.GymMember;
import com.example.gymmemberregistration.util.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GymMemberRVAdapter.GymMemberDelegate{

    //File variables
    File externalFile;
    private String fileName = "MyNewFile.txt";
    private String directoryPath = "MyAppFolder";

    //member id counter

    //intent keys
    public static String MAIN_KEY = "main_key";
    public static int MAIN_REQUEST_CODE = 205;

    private List<GymMember> gymMemberList = new ArrayList<>();

    @BindView(R.id.main_recycle_view)
    public RecyclerView gymMemberRecycleListView;

    @BindView(R.id.register_member_button)
    public Button registerGymMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        externalFile = new File(getExternalFilesDir(directoryPath),fileName);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        gymMemberRecycleListView.addItemDecoration(itemDecoration);

        readFromExternalStorage();

        registerGymMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GymMemberRegistrationActivity.class);

                intent.putExtra(MAIN_KEY, gymMemberList.size()+"");
                if(intent != null)
                startActivityForResult(intent, MAIN_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        externalFile = new File(getExternalFilesDir(directoryPath), fileName);
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == MAIN_REQUEST_CODE) {
            String message = data.getStringExtra(GymMemberRegistrationActivity.FROM_REGISTRATION_KEY);
            message += "\n";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(externalFile, true);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();
                Toast.makeText(this, message + " was written to file", Toast.LENGTH_SHORT).show();
                readFromExternalStorage();
            } catch (IOException e){
                Logger.logError(new Throwable(e.getMessage()));
            }

        }
    }

    private void readFromExternalStorage(){
        String delimiter = ",";
        try{
            gymMemberList = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(externalFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String input = null;

            while ((input = bufferedReader.readLine()) != null){
                Logger.logDebug(input);
                gymMemberList.add(new GymMember(input.split(delimiter)[0], input.split(delimiter)[1], input.split(delimiter)[2]));
            }
            bufferedReader.close();

            /*mainListView.setAdapter(new ReceiptAdapter(receiptList));*/
            gymMemberRecycleListView.setAdapter(new GymMemberRVAdapter(gymMemberList, this));

            //needed to let recycle view know which way to display the information.
            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);

            layoutmanager.setOrientation(RecyclerView.VERTICAL);
            gymMemberRecycleListView.setLayoutManager(layoutmanager);

        }
        catch (IOException e){
            Logger.logError(new Throwable(e.getMessage()));
        }
    }


    @Override
    public void gymMemberSelected(GymMember selectedGymMember) {
        Intent intent = new Intent(this, GymMembersDetailedInfoActivity.class);
        intent.putExtra("my_parcel", selectedGymMember);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
