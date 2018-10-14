package ar.com.instafood.models

import ar.com.instafood.activities.R

data class Restaurant(val title : String, val description : String, val distance : String, val image : Int)

fun getSampleRestaurants() : List<Restaurant>{
    return listOf(
            Restaurant("La Birra Bar","Carlos Calvo 4317, Boedo","1 km", R.drawable.logo_birrabar),
            Restaurant("Aloha"," Av. Chiclana 3299, Parque Patricios","2 km",R.drawable.burgermood),
            Restaurant("Perez-H","Descripcion perez h","10",R.drawable.perezh),
            Restaurant("McDonalds","Descripcion Mcdonalds","20",R.drawable.mcdonalds),
            Restaurant("Burger King","Descripcion Burger King","30",R.drawable.burgerking)
    )
}