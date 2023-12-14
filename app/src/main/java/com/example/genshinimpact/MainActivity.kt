package com.example.genshinimpact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvChara: RecyclerView
    private val list = ArrayList<Chara>()

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvChara = findViewById(R.id.rv_chara)
        rvChara.setHasFixedSize(true)

        list.addAll(getListChara())
        showRecyclerList()
    }

    private fun getListChara(): ArrayList<Chara> {
        val dataName = resources.getStringArray(R.array.data_char)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listChara = ArrayList<Chara>()
        for (i in dataName.indices) {
            val chara = Chara(dataName[i], dataDescription[i], dataPhoto[i])
            listChara.add(chara)
        }
        return listChara
    }

    private fun showRecyclerList() {
        rvChara.layoutManager = LinearLayoutManager(this)
        val listCharAdapter = ListCharAdapter(list)
        rvChara.adapter = listCharAdapter
        listCharAdapter.setOnItemClickListener { clickedChara ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, clickedChara)
            startActivity(intent)
        }
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
