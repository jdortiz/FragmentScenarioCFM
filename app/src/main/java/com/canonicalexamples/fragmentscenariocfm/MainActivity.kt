package com.canonicalexamples.fragmentscenariocfm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            loadFragment()
        }
    }

    private fun loadFragment() {
        val fragment = AFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, fragment)
            .commit()
    }
}
