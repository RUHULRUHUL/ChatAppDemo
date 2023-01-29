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
import com.google.firebase.database.ValueEventListener;
import com.ruhul.chatappdemo.R;
import com.ruhul.chatappdemo.databinding.ActivityBraodCastJavaBinding;
import com.ruhul.chatappdemo.java.model.BroadCastStatusJavaModel;

public class BroadCastJavaActivity extends AppCompatActivity {

    private ActivityBraodCastJavaBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBraodCastJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("BroadCast-Java");


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        //broadcast_status
        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BroadCastStatusJavaModel model = new BroadCastStatusJavaModel(
                        true,
                        "start",
                        "Success",
                        binding.messageEdiText.getText().toString(),
                        "online",
                        "chat"
                );

                databaseReference.child("broadcast_status")
                        .child(binding.idEdiText.getText().toString().trim())
                        .setValue(model)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(BroadCastJavaActivity.this, "Data Upload Success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        binding.getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(binding.idEdiText.getText().toString().trim());
            }
        });


    }

    private void getData(String id) {

        databaseReference.child("broadcast_status")
                .child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("msgLog", snapshot.getValue().toString());

                        BroadCastStatusJavaModel model = snapshot.getValue(BroadCastStatusJavaModel.class);
                        if (model != null) {
                            //binding.nameEdiText.setText(chatData.name)
                            binding.messageEdiText.setText(model.getMessage());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}