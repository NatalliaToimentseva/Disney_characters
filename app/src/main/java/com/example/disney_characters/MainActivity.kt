package com.example.disney_characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.disney_characters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binging: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging?.root)
    }
}