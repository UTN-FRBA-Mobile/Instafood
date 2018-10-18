package ar.com.instafood.models

import android.location.Location
import ar.com.instafood.activities.R

data class Restaurant(val title : String, val description : String, var distance : String,var longitud: Double, var latitud: Double, val image : Int)

fun getSampleRestaurants() : List<Restaurant>{
    return listOf(
            Restaurant("La Birra Bar","Carlos Calvo 4317, Boedo","1",1.0,1.0, R.drawable.logo_birrabar),
            Restaurant("Aloha"," Av. Chiclana 3299, Parque Patricios","2",2.0,2.0,R.drawable.burgermood),
            Restaurant("Perez-H","Descripcion perez h","10",10.0,10.0,R.drawable.perezh),
            Restaurant("McDonalds","Descripcion Mcdonalds","20",20.0,20.0,R.drawable.mcdonalds),
            Restaurant("Burger King","Descripcion Burger King","30",30.0,30.0,R.drawable.burgerking)
    )
}


fun setDistances(restaurants : List<Restaurant>, currentLocation : Location?) : List<Restaurant>{
    return restaurants.map {calculateLoc(currentLocation,it)};
}

fun calculateLoc(currentLocation: Location?, restaurant : Restaurant) : Restaurant {
    var location2 = Location("");
    location2.setLatitude(restaurant.latitud);
    location2.setLongitude(restaurant.longitud);
    restaurant.distance = currentLocation?.distanceTo(location2).toString();
    return restaurant;
}
