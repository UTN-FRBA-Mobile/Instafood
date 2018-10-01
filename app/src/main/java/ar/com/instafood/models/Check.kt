package ar.com.instafood.models

import ar.com.instafood.activities.R

data class Check(val name: String, var products: ArrayList<Product>) {

}

fun getSampleCheck(): ArrayList<Check> {
    return arrayListOf(
            Check("Jose", arrayListOf(Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood), Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood), Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood))),
            Check("Juan", arrayListOf(Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood), Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood), Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood)))
    )
}