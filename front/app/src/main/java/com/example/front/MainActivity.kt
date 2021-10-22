package com.example.front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private var userTransport: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userTransport = findViewById(R.id.userTransport)

    }
}