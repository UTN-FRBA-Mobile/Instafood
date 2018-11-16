package ar.com.instafood.fragments.order

import ar.com.instafood.models.SingleOrder
import com.google.common.collect.Lists

class OrderService {


    fun fetchLastOrder() : List<SingleOrder> {
        var singleOrders = mutableListOf<SingleOrder>()
        singleOrders.add(SingleOrder("Hamburguesa Doble Queso", 1))
        singleOrders.add(SingleOrder("Cocacola 500ml", 2))
        singleOrders.add(SingleOrder("Papas grandes", 1))
        return singleOrders;
    }

}