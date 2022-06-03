package com.ramoncinp.androidautopresentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramoncinp.androidautopresentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AndroidAutoPresentation)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
