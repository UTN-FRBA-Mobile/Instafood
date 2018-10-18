package ar.com.instafood.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.RestaurantAdapter
import ar.com.instafood.interfaces.adapterCallback
import ar.com.instafood.models.Restaurant
import ar.com.instafood.models.getSampleRestaurants
import kotlinx.android.synthetic.main.fragment_search_restaurant.*
import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import ar.com.instafood.models.setDistances


class SearchRestaurantFragment : Fragment() , SeekBar.OnSeekBarChangeListener , adapterCallback {
    private var seekBar : SeekBar? = null
    private var textView : TextView? = null
    private var recyclerViewSearchRestaurant : RecyclerView? = null
    private var locationManager : LocationManager? = null
    private var currentLocation : Location? = null

    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_search_restaurant, container, false)
        textView = view.findViewById(R.id.areaBusquedaRestaurants)
        seekBar = view.findViewById(R.id.seekBarRestaurant)
        recyclerViewSearchRestaurant = view.findViewById(R.id.recyclerViewSearchRestaurant)
        locationManager = activity!!.getSystemService(LOCATION_SERVICE) as LocationManager?;
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        seekBar!!.setOnSeekBarChangeListener(this)
        seekBar!!.progress = 1
        return view;
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = location;
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNegativeClick()
        updatePositiveClick()
        recyclerViewSearchRestaurant?.setHasFixedSize(true)
        recyclerViewSearchRestaurant?.layoutManager = LinearLayoutManager(context)
        updateRestaurants()
    }

    @SuppressLint("MissingPermission")
    private fun updateRestaurants() {
        var rests = setDistances(getSampleRestaurants(),currentLocation)
        Log.e("Restaurants","latitude :" + currentLocation?.latitude.toString() + " longitude: " + currentLocation?.longitude.toString())
        var Restaurants = rests.filter { it.distance.toInt() <= seekBar!!.progress }
        recyclerViewSearchRestaurant?.adapter = RestaurantAdapter(Restaurants,this)
    }

    override fun onItemClick(restoPosition : Int){
        var activity = this.activity
        var intent_result = Intent();
        intent_result.putExtra("position", restoPosition)
        activity!!.setResult(Activity.RESULT_OK, intent_result)
        activity!!.finish()
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
        recyclerViewSearchRestaurant?.adapter = RestaurantAdapter(Restaurants,this)
    }

}
