package ar.com.instafood.adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.MainActivity
import ar.com.instafood.activities.R
import ar.com.instafood.fragments.menuFragments.ProductDetailFragment
import ar.com.instafood.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_product_card.view.*


class MenuProductAdapter(val products : ArrayList<Product>) : RecyclerView.Adapter<MenuProductAdapter.ProductViewHolder>(){

    private var product_image : Int ? = null

    class ProductViewHolder(var card: View) : RecyclerView.ViewHolder(card) {
        init {
            card.setOnClickListener { v: View ->
                var position: Int = adapterPosition
                mContext = v.context

                fragmentJump(card)
            }
        }

        private val mFragment: ProductDetailFragment ? = ProductDetailFragment()
        private var mBundle: Bundle ? = null
        private var mContext: Context? = null

        private fun fragmentJump(card: View) {
            mBundle = Bundle()
            mBundle?.putString("title",card.tv_title.text.toString())
            mBundle?.putString("description",card.tv_description.text.toString())
            mBundle?.putString("price",card.tv_price.text.toString())
            mBundle?.putInt("image",card.iv_icon.id)
            mBundle?.putString("image_string",card.iv_icon_string.text.toString())

            mFragment!!.arguments = mBundle

            switchContent(R.id.fragment_container, mFragment)
        }

        private fun switchContent(id: Int, frag: ProductDetailFragment) {
            if (mContext == null)
                return
            if (mContext is MainActivity) {
                val mainActivity = mContext as MainActivity
                mainActivity.switchContent(id, frag)
            }
        }


    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (holder != null) {
            val product = products[position]
            holder.card.tv_title.text = product.title
            holder.card.tv_description.text = product.description
            holder.card.tv_price.text = product.price.toString()
            Picasso.get().load(product.image).into(holder.card.iv_icon_image)
            holder.card.iv_icon_string.text = product.image.toString()
            //product_image = product.image
        }
    }
    //Create a new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_product_card,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

}