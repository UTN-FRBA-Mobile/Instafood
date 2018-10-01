package ar.com.instafood.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import ar.com.instafood.models.getSampleRestaurants;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView
import ar.com.instafood.adapters.RestaurantAdapter
import ar.com.instafood.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_search_restaurants.*
 import kotlinx.android.synthetic.main.toolbar.*

class SearchRestaurantsActivity : AppCompatActivity() , OnSeekBarChangeListener {
    private var seekBar : SeekBar? = null
    private var textView : TextView? = null

    private val mainFragment : MainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_restaurants)
        textView = this.areaBusquedaRestaurants
        seekBar = this.seekBarRestaurant
        this.seekBar!!.setOnSeekBarChangeListener(this)
        this.seekBar!!.progress = 1
        SetActionBar()
        updateNegativeClick()
        updatePositiveClick()
        recyclerViewSearchRestaurant.setHasFixedSize(true)
        recyclerViewSearchRestaurant.layoutManager = LinearLayoutManager(this)
        updateRestaurants()
        //navigationSearch.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun updateRestaurants() {
        var Restaurants = getSampleRestaurants().filter { it.distance.toInt() <= seekBar!!.progress }
        recyclerViewSearchRestaurant.adapter = RestaurantAdapter(Restaurants)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        when (item.itemId){
            R.id.navigation_home -> transaction.replace(R.id.fragment_container, mainFragment)
        }
        transaction.commit()
        true
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        // called when progress is changed
        textView!!.text = "Área de busqueda :" + progress.toString() + "Kms";
        var Restaurants = getSampleRestaurants().filter{it.distance.toInt() <= progress }
        recyclerViewSearchRestaurant.adapter = RestaurantAdapter(Restaurants)
    }

    private fun updateNegativeClick() {
        minusSignSeekRestaurant.setOnClickListener { it ->
            seekBar!!.progress = seekBar!!.progress - 1;
        }
    }

    private fun updatePositiveClick() {
        plusSignSeekRestaurant.setOnClickListener { it ->
            seekBar!!.progress = seekBar!!.progress + 1;
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is started
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is stopped
    }

    private fun SetActionBar() {
        setSupportActionBar(toolbar)
    }

}

