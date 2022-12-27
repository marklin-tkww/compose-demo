package com.example.myapplication.composeAndview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R

class ComposeAndViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_and_view)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container, ComposeUiFragment.getInstance()).commit()
    }
}