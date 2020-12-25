package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.log_in_page.*
import kotlinx.android.synthetic.main.sign_up_page.*

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)
        auth = FirebaseAuth.getInstance()

        signUp.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        btnLogIn.setOnClickListener{
            doLogIn()
        }
    }

    private fun doLogIn() {
        if(email.text.toString().isEmpty()){
            email.error = "Please enter email!"
            email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Please enter valid email!"
            email.requestFocus()
            return
        }
        if(password.text.toString().isEmpty()){
            password.error = "Please enter password!"
            password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(baseContext, "Log in failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}