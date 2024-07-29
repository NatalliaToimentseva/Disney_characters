package com.example.disney_characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.disney_characters.databinding.ActivityMainBinding
import com.example.disney_characters.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binging: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binging = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binging?.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }
    }
}