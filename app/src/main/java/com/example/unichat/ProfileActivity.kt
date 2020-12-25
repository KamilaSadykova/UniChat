package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.menu_page.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_page)
        auth = FirebaseAuth.getInstance()
        loadProfile()

        btnEdit.setOnClickListener{
            startActivity(Intent(this,EditActivity::class.java))
            finish()
        }
        btnLogout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,LogInActivity::class.java))
            finish()
        }
        profile.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    private fun loadProfile() {
        val user = auth.currentUser
        emailText.text = user?.email
    }
}