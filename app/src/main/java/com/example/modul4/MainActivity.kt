package com.example.modul4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun toAddPost(view: View) {
        val intent = Intent(this, AddPostActivity::class.java)
        startActivity(intent)
    }
}