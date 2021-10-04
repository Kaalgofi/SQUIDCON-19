package com.example.squidcov19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.EditText

class LOGINACTIVITY1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity1)

    }
    fun registeractivity(view:View){
        startActivity(intent, this, REGISTROActivity::class.java))

    }
}