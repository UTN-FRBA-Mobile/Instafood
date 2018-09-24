package ar.com.instafood.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.models.Product
import kotlinx.android.synthetic.main.single_product_card.view.*

class MenuProductAdapter(val products : List<Product>) : RecyclerView.Adapter<MenuProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(val card: View) : RecyclerView.ViewHolder(card)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (holder != null) {
            val product = products[position]
            holder.card.tv_title.text = product.title
            holder.card.tv_description.text = product.description
            holder.card.tv_price.text = product.price.toString()
            holder.card.iv_icon.setImageResource(product.image)
        }
    }
    //Create a new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_product_card,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

}