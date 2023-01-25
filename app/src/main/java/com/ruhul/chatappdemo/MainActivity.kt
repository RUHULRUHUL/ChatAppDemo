package com.ruhul.chatappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruhul.chatappdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*   binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)*/

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        database = Firebase.database.reference

        val chat = Chat(
            "true",
            "start",
            message = binding.messageEdiText.text.toString(),
            "Success",
            "Online",
            "chat",
            name = binding.nameEdiText.text.toString()
        )

/*        binding.postButton.setOnClickListener {
            database.child("broadcast_status").child("user-"+binding.idEdiText.text.toString().trim())
                .setValue(chat)

        }*/

        binding.postButton.setOnClickListener {
            database.child("ping").child("user-" + binding.idEdiText.text.toString().trim())
                .setValue(chat)

        }


    }
}