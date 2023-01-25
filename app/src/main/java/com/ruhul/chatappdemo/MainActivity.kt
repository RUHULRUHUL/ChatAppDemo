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

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        database = Firebase.database.reference

        binding.postButton.setOnClickListener {
            val chat = Chat(
                "true",
                "start",
                binding.messageEdiText.text.toString(),
                "Success",
                "online",
                "chat",
                binding.nameEdiText.text.toString()
            )
            database.child("ping").child(binding.idEdiText.text.toString().trim())
                .setValue(chat)
        }

        binding.getButton.setOnClickListener {


        }


    }
}