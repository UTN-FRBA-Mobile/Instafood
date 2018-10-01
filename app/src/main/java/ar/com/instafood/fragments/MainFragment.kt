package ar.com.instafood.fragments


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ar.com.instafood.activities.R
import ar.com.instafood.activities.SearchRestaurantsActivity
import ar.com.instafood.adapters.MenuTabsAdapter
import ar.com.instafood.adapters.RestaurantAdapter
import ar.com.instafood.models.getSampleRestaurants
import kotlinx.android.synthetic.main.toolbar.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        var view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get reference to button
        val btn_QRScan = getView()?.findViewById<Button>(R.id.QRScan)
        val btn_restaurantSearch = getView()?.findViewById<Button>(R.id.restaurantSearch)
        val recyclerViewSearchRestaurant = getView()?.findViewById<RecyclerView>(R.id.recyclerViewSearchRestaurant)


        // set on-click listener
        btn_QRScan?.setOnClickListener { _ ->

        }
        btn_restaurantSearch?.setOnClickListener { _ ->

        }
        switchSearchRestaurants()

        var restaurants = getSampleRestaurants()
        recyclerViewSearchRestaurant?.setHasFixedSize(true)
        recyclerViewSearchRestaurant?.layoutManager = LinearLayoutManager(context)
        recyclerViewSearchRestaurant?.adapter = RestaurantAdapter(restaurants)
    }

    private fun switchSearchRestaurants() {
        val restSearch = getView()?.findViewById<Button>(R.id.restaurantSearch)
        restSearch?.setOnClickListener { _ ->
            activity?.startActivityForResult(Intent(activity, SearchRestaurantsActivity::class.java),1);
        }
    }

}
