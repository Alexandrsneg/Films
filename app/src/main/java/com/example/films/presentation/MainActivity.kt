package com.example.films.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.films.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun showBackArrowAtToolbar(enable: Boolean) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(enable)
            it.setDisplayShowHomeEnabled(enable)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}