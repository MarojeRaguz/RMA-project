package com.example.barcode.ui.restaurant_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barcode.databinding.FragmentNewPasswordBinding
import com.google.firebase.auth.FirebaseAuth


class NewPasswordFragment:Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        binding = FragmentNewPasswordBinding.inflate(layoutInflater)
        binding.btnChangePassword.setOnClickListener { changePassword() }
        return binding.root
    }

    private fun changePassword() {
        var user = auth.currentUser
        var password = binding.etNewPassword.text
        var confirmPassword = binding.etNewPasswordConfirm.text
        if (confirmPassword.isNullOrEmpty() || password.isNullOrEmpty()){
            Toast.makeText(context, "Morate popuniti sva polja",
                Toast.LENGTH_SHORT).show()
        } else if (password.count() < 6){
            Toast.makeText(context, "Lozinka mora sadržavati barem 6 znakova",
                Toast.LENGTH_SHORT).show()
        } else {
            if (confirmPassword.toString() == password.toString()){

                user!!.updatePassword(binding.etNewPassword.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "lozinka uspješno promijenjena",
                            Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "Došlo je do pogreške, lozinka nije promijenjena",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(context, "nije isto uneseno",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}