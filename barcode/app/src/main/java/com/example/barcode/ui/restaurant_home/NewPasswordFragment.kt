package com.example.barcode.ui.restaurant_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barcode.R
import com.example.barcode.databinding.FragmentNewPasswordBinding
import com.google.firebase.auth.FirebaseAuth


class NewPasswordFragment:Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        binding = FragmentNewPasswordBinding.inflate(layoutInflater)
        binding.btnChangePassword.setOnClickListener { changePassword() }
        return binding.root
    }

    private fun changePassword() {
        val user = auth.currentUser
        val password = binding.etNewPassword.text
        val confirmPassword = binding.etNewPasswordConfirm.text
        if (confirmPassword.isNullOrEmpty() || password.isNullOrEmpty()){
            Toast.makeText(context, getString(R.string.password_change_all_fields_require),
                Toast.LENGTH_SHORT).show()
        } else if (password.count() < 6){
            Toast.makeText(context, getString(R.string.password_change_at_least_6_characters),
                Toast.LENGTH_SHORT).show()
        } else {
            if (confirmPassword.toString() == password.toString()){

                user!!.updatePassword(binding.etNewPassword.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, getString(R.string.password_succsesfully_changed),
                            Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, getString(R.string.password_change_error),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(context, getString(R.string.password_change_passwords_not_equals),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}