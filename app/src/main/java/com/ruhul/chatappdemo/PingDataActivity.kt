package com.ruhul.chatappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruhul.chatappdemo.databinding.ActivityGetUserDataBinding

class PingDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetUserDataBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference

        binding.toolbar.title = "Ping"

        //ping
        binding.postButton.setOnClickListener {
            val ping = Ping(
                "25-1-23",
                binding.brodCastIdEdiText.text.toString(),
                binding.fcmToken.text.toString(),
                "Online"
            )
            database.child("ping").child(binding.userId.text.toString().trim())
                .setValue(ping)
        }

        binding.getButton.setOnClickListener {
            getData(binding.userId.text.toString())
        }

    }

    private fun getData(id: String) {

        database.child("ping").child(id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("msgLog", dataSnapshot.value.toString())
                    val ping: Ping? = dataSnapshot.getValue(Ping::class.java)
                    if (ping != null) {
                        binding.fcmToken.setText(ping.fcm_token)
                        binding.brodCastIdEdiText.setText(ping.broadcast_id)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("msgLog", "Error msg = ${error.message}")
                }

            })
    }
}