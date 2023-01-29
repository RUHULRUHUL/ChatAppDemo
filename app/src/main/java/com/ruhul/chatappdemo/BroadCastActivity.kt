package com.ruhul.chatappdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruhul.chatappdemo.databinding.ActivityMainBinding


class BroadCastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.toolbar.title = "broadcast_status"

        auth = Firebase.auth
        database = Firebase.database.reference

        binding.logOutButton.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(this, "log out success", Toast.LENGTH_SHORT).show()
/*            startActivity(Intent(this, SignInActivity::class.java))
            finish()*/
        }


        binding.pingActivity.setOnClickListener {
            startActivity(Intent(this, PingDataActivity::class.java))

        }

        //broadcast_status
        binding.postButton.setOnClickListener {

            if (auth.currentUser != null) {
                val chat = Chat(
                    true,
                    "start",
                    "Success",
                    binding.messageEdiText.text.toString(),
                    "online",
                    "chat"
                )
                database.child("broadcast_status")
                    .child(binding.idEdiText.text.toString().trim())
                    .setValue(chat)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "broadcast_status data save success",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "broadcast_status data save fail",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
            }else{
                Toast.makeText(this, "not Authorization please logIn", Toast.LENGTH_SHORT).show()
            }


        }

        binding.getButton.setOnClickListener {

            if (auth.currentUser != null) {
                val id = binding.idEdiText.text.toString().trim()
                getData(id)
            }else{
                Toast.makeText(this, "not Authorization please logIn", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getSpecificQueryData() {
        database.child("broadcast_status")
            .orderByChild("message")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("orderByChild", snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        database.child("broadcast_status")
            .orderByChild("message")
            .limitToFirst(10)
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("orderByChild", snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun getAllData() {
        database.child("broadcast_status").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("broadcast_status", snapshot.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getData(id: String) {

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