package ar.com.instafood.models

import ar.com.instafood.activities.R

data class Product(val title : String, val description : String, val price : Int, val image : Int) {
    var listProduct : ArrayList<Product> = arrayListOf<Product>()


    fun addNewOne(product: Product) {
        listProduct.add(product)
    }

    fun getProducList(): ArrayList<Product> {
        return listProduct
    }


}

fun getSampleProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("180 gr de burger", "Descripcion: 180 gr burger - panceta - huevo", 220, R.drawable.burger180),
            Product("Burger mood", "Descripcion: Happy Burger con salsa picante", 500, R.drawable.burgermood),
            Product("Perez-H", "Descripcion: Burger raton perez con palmito", 100, R.drawable.perezh),
            Product("McDonalds", "Descripcion: Burger Industrial con \"carne\"", 120, R.drawable.mcdonalds),
            Product("Burger King", "Descripcion: Carne del rey con mayonesa", 30, R.drawable.burgerking)
    )
}


fun getMainProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("180 gr de burger", "Descripcion: 180 gr burger - panceta - huevo", 220, R.drawable.burger180),
            Product("Burger mood", "Descripcion: Happy Burger con salsa picante", 500, R.drawable.burgermood),
            Product("Perez-H", "Descripcion: Burger raton perez con palmito", 100, R.drawable.perezh),
            Product("McDonalds", "Descripcion: Burger Industrial con \"carne\"", 120, R.drawable.mcdonalds),
            Product("Burger King", "Descripcion: Carne del rey con mayonesa", 30, R.drawable.burgerking)
    )
}

fun getDrinkProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("Birra Artesanal","Descripcion: de la mejor",220, R.drawable.burger180),
            Product("Coca Cola","Descripcion: mucho azucar",50, R.drawable.burger180),
            Product("Sprite","Descripcion: alimonada",220, R.drawable.burger180),
            Product("Fernet","Descripcion: Fernet BRanca",220, R.drawable.burger180),
            Product("Birra","Descripcion: Heineken",220, R.drawable.burger180)
    )
}

fun getSecondaryProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("Papas con chedart","Descripcion: papas - panceta - chedart",140, R.drawable.burger180),
            Product("salchichitas","Descripcion: salchis",150, R.drawable.burger180),
            Product("tabla de jamones","Descripcion: jamoncito",200, R.drawable.burger180),
            Product("bastones de muzzarella","Descripcion: bastoncitos de queso",120, R.drawable.burger180)
    )
}