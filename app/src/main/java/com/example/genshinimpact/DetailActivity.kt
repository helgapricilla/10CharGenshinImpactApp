package com.example.genshinimpact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {

    private val charaList: MutableList<Chara> = mutableListOf()

    private fun populateCharaList() {
        // Populate the list with Chara objects
        val dataName = resources.getStringArray(R.array.data_char)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        for (i in dataName.indices) {
            val chara = Chara(dataName[i], dataDescription[i], dataPhoto[i])
            charaList.add(chara)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        populateCharaList()


        val chara = intent.getParcelableExtra<Chara>(MainActivity.INTENT_PARCELABLE)

        val photo = findViewById<ImageView>(R.id.imgchar)
        val name = findViewById<TextView>(R.id.tvcharname)
        val description = findViewById<TextView>(R.id.tvchardetail)

        chara?.let {
            val dataPhotoArray = resources.getStringArray(R.array.data_photo)

            // Find the index of the Chara object in the list
            val position = charaList.indexOf(it)

            // Assuming 'position' is the position of the Chara object in your list
            val imageUrl = dataPhotoArray.getOrNull(position) ?: ""

            // Use Glide to load the image from the URL
            Glide.with(this)
                .load(imageUrl)
                .into(photo)

            name.text = it.name    // Assuming 'name' is a property in Chara representing the name
            description.text =
                it.description  // Assuming 'description' is a property in Chara representing the description
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}