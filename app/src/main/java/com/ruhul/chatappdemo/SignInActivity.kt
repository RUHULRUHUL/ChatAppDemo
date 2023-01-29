package com.ruhul.chatappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ruhul.chatappdemo.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.toolbar.title = "log In Registration"

        binding.signUpButton.setOnClickListener {
            signUp()
        }


        binding.logInButton.setOnClickListener {
            signIn()
        }

    }

    public override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Toast.makeText(this, "Already Authorized User", Toast.LENGTH_SHORT).show()
            gotoMainActivity()

        } else {
            Toast.makeText(this, "Please Log in First", Toast.LENGTH_SHORT).show()
        }
    }

    private fun gotoMainActivity() {
        startActivity(Intent(this, BroadCastActivity::class.java))
        finish()
    }

    private fun signUp() {

        Toast.makeText(
            this, "signUp",
            Toast.LENGTH_SHORT
        ).show()

        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEdiText.text.isNotEmpty()) {

            auth.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEdiText.text.toString().trim()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, BroadCastActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        } else {
            Toast.makeText(this, "check this field", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signIn() {

        Toast.makeText(
            this, "signIn",
            Toast.LENGTH_SHORT
        ).show()

        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEdiText.text.isNotEmpty()) {
            auth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEdiText.text.toString().trim()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        gotoMainActivity()

                    } else {
                        Toast.makeText(
                            this, "log in Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        } else {
            Toast.makeText(this, "check this field", Toast.LENGTH_SHORT).show()
        }
    }


}