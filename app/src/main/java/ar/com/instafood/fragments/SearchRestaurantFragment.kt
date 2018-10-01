package ar.com.instafood.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.RestaurantAdapter
import ar.com.instafood.models.getSampleRestaurants
import kotlinx.android.synthetic.main.fragment_search_restaurant.*

class SearchRestaurantFragment : Fragment() , SeekBar.OnSeekBarChangeListener {
    private var seekBar : SeekBar? = null
    private var textView : TextView? = null
    private var recyclerViewSearchRestaurant : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_search_restaurant, container, false)
        textView = view.findViewById(R.id.areaBusquedaRestaurants)
        seekBar = view.findViewById(R.id.seekBarRestaurant)
        recyclerViewSearchRestaurant = view.findViewById(R.id.recyclerViewSearchRestaurant)
        seekBar!!.setOnSeekBarChangeListener(this)
        seekBar!!.progress = 1
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNegativeClick()
        updatePositiveClick()
        recyclerViewSearchRestaurant?.setHasFixedSize(true)
        recyclerViewSearchRestaurant?.layoutManager = LinearLayoutManager(context)
        updateRestaurants()
    }

    private fun updateRestaurants() {
        var Restaurants = getSampleRestaurants().filter { it.distance.toInt() <= seekBar!!.progress }
        recyclerViewSearchRestaurant?.adapter = RestaurantAdapter(Restaurants)
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

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        textView!!.text = "Área de busqueda: " + progress.toString() + " Kms";
        var Restaurants = getSampleRestaurants().filter{it.distance.toInt() <= progress }
        recyclerViewSearchRestaurant?.adapter = RestaurantAdapter(Restaurants)
    }
}
