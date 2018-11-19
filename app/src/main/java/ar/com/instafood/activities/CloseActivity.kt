package ar.com.instafood.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ar.com.instafood.fragments.*
import kotlinx.android.synthetic.main.toolbar.*

class CloseActivity : AppCompatActivity() {

    private val fragment : CloseFragment

    init {
        fragment = CloseFragment()
    }

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_close)
        SetActionBar()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.close_fragment_container, fragment)
        transaction.commit()
    }

    private fun SetActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Cierre de mesa")
    }

}