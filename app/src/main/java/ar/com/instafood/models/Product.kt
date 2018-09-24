package ar.com.instafood.models

import ar.com.instafood.activities.R

data class Product(val title : String, val description : String, val price : Int, val image : Int) {
    var listProduct : ArrayList<Product> = arrayListOf<Product>()


    fun addNewOne(product: Product) {
        listProduct.add(product)
    }

    fun getSampleProducts(): List<Product> {
        return listOf(
                Product("180 gr de burger", "Descripcion: 180 gr burger - panceta - huevo", 220, R.drawable.burger180),
                Product("Burger mood", "Descripcion: Happy Burger con salsa picante", 500, R.drawable.burgermood),
                Product("Perez-H", "Descripcion: Burger raton perez con palmito", 100, R.drawable.perezh),
                Product("McDonalds", "Descripcion: Burger Industrial con \"carne\"", 120, R.drawable.mcdonalds),
                Product("Burger King", "Descripcion: Carne del rey con mayonesa", 30, R.drawable.burgerking)
        )
    }

    fun getProducList(): List<Product> {
        return listProduct
    }
}
fun getSampleProducts(): List<Product> {
    return listOf(
            Product("180 gr de burger", "Descripcion: 180 gr burger - panceta - huevo", 220, R.drawable.burger180),
            Product("Burger mood", "Descripcion: Happy Burger con salsa picante", 500, R.drawable.burgermood),
            Product("Perez-H", "Descripcion: Burger raton perez con palmito", 100, R.drawable.perezh),
            Product("McDonalds", "Descripcion: Burger Industrial con \"carne\"", 120, R.drawable.mcdonalds),
            Product("Burger King", "Descripcion: Carne del rey con mayonesa", 30, R.drawable.burgerking)
    )
}