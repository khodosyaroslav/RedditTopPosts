package edu.fpm.reddittopposts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.fpm.reddittopposts.R
import edu.fpm.reddittopposts.databinding.ActivityMainBinding
import edu.fpm.reddittopposts.databinding.PostItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}