package ar.com.instafood.models

import ar.com.instafood.activities.R

data class Check(val name: String, var products: ArrayList<Product>)

fun getSampleCheck(): ArrayList<Check> {
    return arrayListOf(
            Check("Diego Pedro", arrayListOf(
                    Product("Burger Facts", "Triple Carne con Provoleta, Panceta, Cebolla Crispy, Queso Dambo y salsa de mayochurri", 240, R.drawable.burgerfacts),
                    Product("Burger Love", "Doble carne, cheddar, dambo, cebolla morada, pepinos, panceta, cebolla crocrante y mucho alioli", 220, R.drawable.burgerlove),
                    Product("Donkey Donuts", "200 gr de carne con donas glaseadas, panceta y salsa de mostaza", 30, R.drawable.donut))),
            Check("Juan Jose", arrayListOf(
                    Product("Premium", "Queso cheddar, pancete, cebollas caramelizadas y tomate", 170, R.drawable.premium),
                    Product("HeartBreaker", "200 gr de carne, pulled pork, queso cheddar, tomate, pepino, cebolla crocante y BBQ", 200, R.drawable.heartbreaker)))
    )
}