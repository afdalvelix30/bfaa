package com.dicoding.githubuserapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.githubuserapp.R
import com.dicoding.githubuserapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.about)
    }
}