package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.compose.ComposeActivity
import com.example.myapplication.composeAndview.ui.ComposeAndViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 纯Compose布局
        val btnCompose = findViewById<Button>(R.id.btn_compose)
        btnCompose.setOnClickListener {
            startActivity(Intent(this, ComposeActivity::class.java))
        }

        //Compose + View
        val btnComposeAndView = findViewById<Button>(R.id.btn_compose_and_view)
        btnComposeAndView.setOnClickListener {
            startActivity(Intent(this, ComposeAndViewActivity::class.java))
        }

    }
}