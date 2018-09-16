package ar.com.instafood.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*
import android.content.Intent
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SetActionBar()
        scanQR()

    }

    private fun scanQR() {
        scanQR.setOnClickListener { _ ->
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    private fun SetActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("InstaFood")
    }
}
