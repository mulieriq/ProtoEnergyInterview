package com.skylabstechke.protoenergyinterview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skylabstechke.protoenergyinterview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Payment Options"

    }
}