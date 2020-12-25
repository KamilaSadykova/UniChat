package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_up_page.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_page)
        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener() {
            singUpUser()
        }
    }

    private fun singUpUser(){
//        if(username.text.toString().isEmpty()){
//            username.error = "Please enter email!"
//            username.requestFocus()
//            return
//        }
        if(new_email.text.toString().isEmpty()){
            new_email.error = "Please enter email!"
            new_email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(new_email.text.toString()).matches()){
            new_email.error = "Please enter valid email!"
            new_email.requestFocus()
            return
        }
        if(new_password.text.toString().isEmpty()){
            new_password.error = "Please enter password!"
            new_password.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(new_email.text.toString(), new_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,LogInActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}

