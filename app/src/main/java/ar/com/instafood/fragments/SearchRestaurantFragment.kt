package ar.com.instafood.fragments

import android.annotation.SuppressLint
import android.content.Intent
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
import ar.com.instafood.interfaces.adapterCallback
import ar.com.instafood.models.Restaurant
import kotlinx.android.synthetic.main.fragment_search_restaurant.*
import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Handler
import android.widget.Toast
import ar.com.instafood.interfaces.RestaurantsService
import ar.com.instafood.models.setDistances
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SearchRestaurantFragment : Fragment() , SeekBar.OnSeekBarChangeListener , adapterCallback {
    private var seekBar : SeekBar? = null
    private var textView : TextView? = null
    private var spinner : View? = null
    private var adapter : RestaurantAdapter? = null
    private var recyclerViewSearchRestaurant : RecyclerView? = null
    private var locationManager : LocationManager? = null
    private var disposable: Disposable? = null
    private var currentLocation : Location? = null
    private var self = this;
    private val restaurantAPIServe by lazy {
        RestaurantsService.create()
    }
    private var restaurants : List<Restaurant>? = null

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
            return view;
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = location;
            if(restaurants !== null) {
                setDistances(restaurants, currentLocation)
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = view.findViewById(R.id.progress_bar_loading);
        spinner!!.postInvalidateOnAnimation()
        textView!!.setVisibility(View.INVISIBLE)
        minusSignSeekRestaurant.setVisibility(View.INVISIBLE)
        plusSignSeekRestaurant.setVisibility(View.INVISIBLE)
        seekBar!!.setVisibility(View.INVISIBLE)
        spinner!!.setVisibility(View.VISIBLE)
        val handler = Handler()
        handler.postDelayed({ getRests()
        }, 800)
        updateNegativeClick()
        updatePositiveClick()
        recyclerViewSearchRestaurant?.setHasFixedSize(true)
        recyclerViewSearchRestaurant?.layoutManager = LinearLayoutManager(context)
}

    @SuppressLint("MissingPermission")
    private fun updateRestaurants() {
        adapter!!.items = restaurants!!.filter { it.distance.toDouble() <= seekBar!!.progress }
        adapter!!.notifyDataSetChanged()
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

    private fun getRests() {
        if (recyclerViewSearchRestaurant is RecyclerView) {
            with(view) {
                adapter = RestaurantAdapter(restaurants,self);
                recyclerViewSearchRestaurant!!.adapter = adapter
                disposable = restaurantAPIServe.getRestaurants().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> adapter!!.items = result.restaurants
                                    adapter!!.notifyDataSetChanged()
                                    if(result.restaurants !== null){
                                        setDistances(result.restaurants,currentLocation)
                                        restaurants = result.restaurants
                                        textView!!.setVisibility(View.VISIBLE)
                                        minusSignSeekRestaurant.setVisibility(View.VISIBLE)
                                        plusSignSeekRestaurant.setVisibility(View.VISIBLE)
                                        seekBar!!.setVisibility(View.VISIBLE)
                                        spinner!!.setVisibility(View.INVISIBLE)
                                    }},
                                { error -> Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show() }
                        )
            }
        }

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        textView!!.text = "Área de busqueda: " + progress.toString() + " Kms";
        updateRestaurants()
    }

}
