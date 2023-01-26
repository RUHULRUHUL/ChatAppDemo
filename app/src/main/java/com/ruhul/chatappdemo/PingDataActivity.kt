package com.ruhul.chatappdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruhul.chatappdemo.databinding.ActivityGetUserDataBinding
import java.text.SimpleDateFormat
import java.util.*

class PingDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetUserDataBinding
    private lateinit var database: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference

        binding.toolbar.title = "Ping"

        auth = Firebase.auth

        //ping
        binding.postButton.setOnClickListener {

            if (auth.currentUser != null) {
                val ping = Ping(
                    ServerValue.TIMESTAMP,
                    "",
                    binding.brodCastIdEdiText.text.toString(),
                    binding.fcmToken.text.toString(),
                    "Online",
                )
                database.child("ping").child(binding.userId.text.toString().trim())
                    .setValue(ping).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "ping Data Save Success", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }else{
                Toast.makeText(this, "not Authorization please logIn", Toast.LENGTH_SHORT).show()
            }

        }

        binding.getButton.setOnClickListener {
            if (auth.currentUser != null) {
                val id = binding.userId.text.toString().trim()
                getData(id)
            }else{
                Toast.makeText(this, "not Authorization please logIn", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getData(id: String) {

        database.child("ping").child(id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("msgLog", dataSnapshot.value.toString())
                    val ping: PingModelGet? = dataSnapshot.getValue(PingModelGet::class.java)
                    timeConvertToDate(ping?.host_last_updated_at)

                    if (ping != null) {
                        binding.brodCastIdEdiText.setText(ping.broadcast_id)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("msgLog", "Error msg = ${error.message}")
                }

            })
    }

    private fun timeConvertToDate(hostLastUpdatedAt: Long?) {

        if ((hostLastUpdatedAt != null) && (hostLastUpdatedAt > 0)) {
            try {
                val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

                val time: String = timeFormat.format(hostLastUpdatedAt)
                val date: String = dateFormat.format(hostLastUpdatedAt)
                Log.d("timeConvertToDate", "date: $date $time")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}