package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.edit_page.*
import kotlinx.android.synthetic.main.menu_page.*
import kotlinx.android.synthetic.main.sign_up_page.*

class EditActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_page)
        auth = FirebaseAuth.getInstance()

        btnSave.setOnClickListener{
            changePassword()
        }
    }

    private fun changePassword() {

        if(currentPassword.text.toString().isNotEmpty() &&
                editPassword.text.toString().isNotEmpty() &&
                confirmPassword.text.toString().isNotEmpty()){
                if(editPassword.text.toString().equals(confirmPassword.text.toString())){
                    val user = auth.currentUser
                    if(user != null && user.email != null){
                        val credential = EmailAuthProvider
                            .getCredential(user.email!!, currentPassword.text.toString())

                        user?.reauthenticate(credential)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    Toast.makeText(this,"Re-authentication success!", Toast.LENGTH_SHORT).show()
                                    user?.updatePassword(editPassword.text.toString())
                                        .addOnCompleteListener {task ->
                                            if(task.isSuccessful){
                                                Toast.makeText(this,"Password changed successfully!", Toast.LENGTH_SHORT).show()
                                                auth.signOut()
                                                startActivity(Intent(this,LogInActivity::class.java))
                                                finish()
                                            }
                                        }
                                } else {
                                    Toast.makeText(this,"Re-authentication failed!", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        startActivity(Intent(this,LogInActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this,"Confirm password doesn't match!", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this,"Please enter all the fields!", Toast.LENGTH_SHORT).show()
        }

    }
}