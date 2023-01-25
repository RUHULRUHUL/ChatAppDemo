package com.ruhul.chatappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
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
            val time = ServerValue.TIMESTAMP

            Log.d("time", "time = $time")

            val ping = Ping(
                ServerValue.TIMESTAMP,
                "",
                binding.brodCastIdEdiText.text.toString(),
                binding.fcmToken.text.toString(),
                "Online",
            )
            database.child("ping2").child(binding.userId.text.toString().trim())
                .setValue(ping)
        }

        binding.getButton.setOnClickListener {
           val id = binding.userId.text.toString().trim()
            getData(id)
        }

    }

    private fun getData(id: String) {

        database.child("ping2").child(id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("msgLog", dataSnapshot.value.toString())
                    val ping: Ping? = dataSnapshot.getValue(Ping::class.java)
                    if (ping != null) {
                        binding.brodCastIdEdiText.setText(ping.broadcast_id)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("msgLog", "Error msg = ${error.message}")
                }

            })
    }
}