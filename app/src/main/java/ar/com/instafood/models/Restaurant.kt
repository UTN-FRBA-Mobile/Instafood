package ar.com.instafood.models

import android.location.Location
import ar.com.instafood.activities.R

data class Restaurant(val title: String, val description: String, var distance: String, var longitud: Double, var latitud: Double, val image: Int)

fun getSampleRestaurants(): List<Restaurant> {
    return listOf(
            Restaurant("La Birra Bar", "Carlos Calvo 4317, Boedo", "1", -58.3740076  , -34.6044714, R.drawable.logo_birrabar),
            Restaurant("Aloha", " Av. Chiclana 3299, Parque Patricios", "2", -58.3740077, -34.6044643, R.drawable.burgermood),
            Restaurant("Perez-H", "Descripcion perez h", "10", -58.3740098, -34.6044669, R.drawable.perezh),
            Restaurant("McDonalds", "Descripcion Mcdonalds", "20", -58.3740079, -34.6044692, R.drawable.mcdonalds),
            Restaurant("Burger King", "Descripcion Burger King", "30", -58.3740082, -34.6044711, R.drawable.burgerking)
    )
}


fun setDistances(restaurants: List<Restaurant>, currentLocation: Location?) {
    return restaurants.forEach { it -> calculateLoc(currentLocation, it) };
}

fun calculateLoc(currentLocation: Location?, restaurant: Restaurant): Restaurant {
    var location2 = Location("");
    location2.setLatitude(restaurant.latitud);
    location2.setLongitude(restaurant.longitud);
    restaurant.distance = currentLocation?.distanceTo(location2)!!.div(1000).toString();
    return restaurant;
}
