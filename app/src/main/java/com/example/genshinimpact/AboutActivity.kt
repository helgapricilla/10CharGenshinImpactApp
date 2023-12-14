package com.example.genshinimpact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageUrl = resources.getString(R.string.about_photo)
        val imgAbout = findViewById<ImageView>(R.id.imgAbout)

        // Load the image using Glide
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image) // Placeholder image
            .into(imgAbout)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

