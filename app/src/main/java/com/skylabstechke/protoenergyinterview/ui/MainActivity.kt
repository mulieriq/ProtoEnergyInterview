package com.skylabstechke.protoenergyinterview.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skylabstechke.protoenergyinterview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.apply {
            title = "Payment Options"
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_menu_canceled -> {
                Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show()
                true
            }
            R.id.filter_menu_pending -> {
                Toast.makeText(this, "Pending", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.payment_options_menu, menu)
        return true
    }
}