package ar.com.instafood.models

import ar.com.instafood.activities.R

data class Restaurant(val title : String, val description : String, val distance : String, val image : Int)

fun getSampleRestaurants() : List<Restaurant>{
    return listOf(
            Restaurant("180 burger","Descripcion: 180 burger","1", R.drawable.burger180),
            Restaurant("Burger mood","Descripcion burger mood","5",R.drawable.burgermood),
            Restaurant("Perez-H","Descripcion perez h","10",R.drawable.perezh),
            Restaurant("McDonalds","Descripcion Mcdonalds","20",R.drawable.mcdonalds),
            Restaurant("Burger King","Descripcion Burger King","30",R.drawable.burgerking)
    )
}