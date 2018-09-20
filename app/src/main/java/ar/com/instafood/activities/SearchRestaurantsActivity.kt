package ar.com.instafood.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ar.com.instafood.Models.getSampleRestaurants
import ar.com.instafood.adapters.RestaurantAdapter
import kotlinx.android.synthetic.main.activity_search_restaurants.*

class SearchRestaurantsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_restaurants)

        recyclerViewSearchRestaurant.setHasFixedSize(true)
        recyclerViewSearchRestaurant.layoutManager = LinearLayoutManager(this)
        recyclerViewSearchRestaurant.adapter = RestaurantAdapter(getSampleRestaurants())
    }
}
