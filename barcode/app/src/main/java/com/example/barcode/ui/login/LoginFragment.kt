package com.example.barcode.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.util.Log.WARN
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barcode.databinding.FragmentLoginBinding
import com.example.barcode.ui.restaurant_home.RestaurantHomeActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.logging.Level.INFO

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.btnLogin.setOnClickListener { login() }
        return binding.root
    }

    private fun login() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        if (email.isNullOrEmpty() and password.isNullOrEmpty()){
            Toast.makeText(context, "Username and password cannot be empty",
                Toast.LENGTH_SHORT).show()
        }else{
            Log.d(TAG,"attempting to sign in user with email: $email")
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(context,RestaurantHomeActivity::class.java).apply{
                        putExtra("user",user)
                    }
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}