package com.ruhul.chatappdemo.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.ruhul.chatappdemo.PingModelGet;
import com.ruhul.chatappdemo.databinding.ActivityPingJavaBinding;
import com.ruhul.chatappdemo.java.model.PingJavaModel;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PingJavaActivity extends AppCompatActivity {

    private ActivityPingJavaBinding binding;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPingJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarTitle.setText("Ping -Java");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PingJavaModel model = new PingJavaModel(
                        ServerValue.TIMESTAMP,
                        "",
                        binding.brodCastIdEdiText.getText().toString(),
                        binding.fcmToken.getText().toString(),
                        "Online"
                );

                databaseReference.child("ping")
                        .setValue(model)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(PingJavaActivity.this, "Ping Data Save Success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        binding.getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(binding.userId.getText().toString().trim());
            }
        });


    }

    private void getData(String id) {

        databaseReference.child("ping").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("msgLog", snapshot.getValue().toString());

                        PingModelGet model =  snapshot.getValue(PingModelGet.class);

                       timeConvertToDate(model.getHost_last_updated_at());

                        if (model != null) {
                            binding.brodCastIdEdiText.setText(model.getBroadcast_id());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void timeConvertToDate(Long host_last_updated_at) {

        if ((host_last_updated_at != null) && (host_last_updated_at > 0)) {
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

                String time = timeFormat.format(host_last_updated_at);
                String date = dateFormat.format(host_last_updated_at);
                Log.d("timeConvertToDate", "datetime = "+date +"Time "+time);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}