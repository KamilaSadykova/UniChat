package com.example.unichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.join_page.*

class JoinTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join_page)
        back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        btnJoin.setOnClickListener{
            startActivity(Intent(this,ChatRoomActivity::class.java))
            finish()
        }
    }
}