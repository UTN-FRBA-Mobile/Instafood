package ar.com.instafood.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import ar.com.instafood.models.getSampleRestaurants;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView
import ar.com.instafood.adapters.RestaurantAdapter
import kotlinx.android.synthetic.main.activity_search_restaurants.*

class SearchRestaurantsActivity : AppCompatActivity() , OnSeekBarChangeListener {
    private var seekBar : SeekBar? = null
    private var textView : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_restaurants)

        seekBar = this.seekBarRestaurant
        textView = this.areaBusquedaRestaurants
        this.seekBar!!.setOnSeekBarChangeListener(this)
        this.seekBar!!.progress = 1;
        minusSignSeekRestaurant.setOnClickListener { it ->
            seekBar!!.progress = seekBar!!.progress - 1;
        }

        plusSignSeekRestaurant.setOnClickListener {
            it -> seekBar!!.progress = seekBar!!.progress + 1;
        }
        recyclerViewSearchRestaurant.setHasFixedSize(true)
        recyclerViewSearchRestaurant.layoutManager = LinearLayoutManager(this)
        var Restaurants = getSampleRestaurants().filter{it.distance.toInt() <= seekBar!!.progress }
        recyclerViewSearchRestaurant.adapter = RestaurantAdapter(Restaurants)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        // called when progress is changed
        textView!!.text = "Área de busqueda :" + progress.toString() + "Kms";
        var Restaurants = getSampleRestaurants().filter{it.distance.toInt() <= progress }
        recyclerViewSearchRestaurant.adapter = RestaurantAdapter(Restaurants)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is started
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is stopped
    }
}

