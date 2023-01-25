package com.ruhul.chatappdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruhul.chatappdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.toolbar.title = "broadcast_status"


        database = Firebase.database.reference

        binding.pingActivity.setOnClickListener {
             startActivity(Intent(this,PingDataActivity::class.java))

        }

        //broadcast_status
        binding.postButton.setOnClickListener {
            val chat = Chat(
                "true",
                "start",
                binding.messageEdiText.text.toString(),
                "Success",
                "online",
                "chat"
            )
            database.child("broadcast_status").child(binding.idEdiText.text.toString().trim()).setValue(chat)
        }

        binding.getButton.setOnClickListener {
            val id = binding.idEdiText.text.toString().trim()
            getData(id)

        }


    }
    private fun getData(id: String){

        database.child("broadcast_status").child(id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("msgLog", dataSnapshot.value.toString())

                    val chatData: Chat? = dataSnapshot.getValue(Chat::class.java)
                    if (chatData != null) {
                        //binding.nameEdiText.setText(chatData.name)
                        binding.messageEdiText.setText(chatData.message)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                  Log.d("msgLog", "Error msg = ${error.message}")
                }

            })
    }
}