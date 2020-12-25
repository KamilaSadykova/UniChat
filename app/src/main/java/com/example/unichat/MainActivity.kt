package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        auth = FirebaseAuth.getInstance()

        profile.setOnClickListener{
            startActivity(Intent(this,ProfileActivity::class.java))
            finish()
        }
        search.setOnClickListener{
                    startActivity(Intent(this,SearchActivity::class.java))
                    finish()
                }
        addGroup.setOnClickListener{
                    startActivity(Intent(this,ChatRoomActivity::class.java))
                    finish()
                }
    }
}