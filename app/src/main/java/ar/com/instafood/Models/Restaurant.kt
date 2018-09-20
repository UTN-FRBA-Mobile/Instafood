package ar.com.instafood.Models

import ar.com.instafood.activities.R

data class Restaurant(val title : String, val description : String, val image : Int)

fun getSampleRestaurants() : List<Restaurant>{
    return listOf(
            Restaurant("180 burger","Descripcion 180 burger", R.drawable.burger180),
            Restaurant("Burger mood","Descripcion burger mood",R.drawable.burgermood),
            Restaurant("Perez-H","Descripcion perez h",R.drawable.perezh)
    )
}