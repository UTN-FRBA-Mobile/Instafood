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
            Product("180 gr de burger", "180 gr burger - panceta - huevo", 220, R.drawable.burger180),
            Product("Burger mood", "Happy Burger con salsa picante", 500, R.drawable.burgermood),
            Product("Perez-H", "Burger raton perez con palmito", 100, R.drawable.perezh),
            Product("McDonalds", "Burger Industrial con \"carne\"", 120, R.drawable.mcdonalds),
            Product("Burger King", "Carne del rey con mayonesa", 30, R.drawable.burgerking)
    )
}


fun getMainProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("Burger Facts", "Criolla doble con panceta y cebolla crocante", 240, R.drawable.burgerfacts),
            Product("Burger Love", "Doble carne, cheddar, dambo, cebolla morada, pepinos, panceta, cebolla crocrante y mucho alioli", 220, R.drawable.burgerlove),
            Product("Premium", "Queso cheddar, pancete, cebollas caramelizadas y tomate", 170, R.drawable.premium),
            Product("HeartBreaker", "200 gr de carne, pulled pork, queso cheddar, tomate, pepino, cebolla crocante y BBQ", 200, R.drawable.heartbreaker),
            Product("Donkey Donuts", "200 gr de carne con donas glaseadas, panceta y salsa de mostaza", 30, R.drawable.donut)
    )
}

fun getDrinkProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("Birra Artesanal","de la mejor",220, R.drawable.burger180),
            Product("Coca Cola","mucho azucar",50, R.drawable.burger180),
            Product("Sprite","alimonada",220, R.drawable.burger180),
            Product("Fernet","Fernet BRanca",220, R.drawable.burger180),
            Product("Birra","Heineken",220, R.drawable.burger180)
    )
}

fun getSecondaryProducts(): ArrayList<Product> {
    return arrayListOf(
            Product("Papas con chedart","papas - panceta - chedart",140, R.drawable.burger180),
            Product("salchichitas","salchis",150, R.drawable.burger180),
            Product("tabla de jamones","jamoncito",200, R.drawable.burger180),
            Product("bastones de muzzarella","bastoncitos de queso",120, R.drawable.burger180)
    )
}