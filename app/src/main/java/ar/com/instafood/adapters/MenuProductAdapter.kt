package ar.com.instafood.adapters

import android.R.attr.contextClickable
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.models.Product
import kotlinx.android.synthetic.main.single_product_card.view.*
import android.content.DialogInterface
import android.R.string.cancel
import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import com.google.android.gms.common.internal.DowngradeableSafeParcel.DowngradeableSafeParcelHelper.putParcelable
import android.os.Bundle
import ar.com.instafood.fragments.menuFragments.ProductDetailFragment
import android.R.attr.fragment
import android.app.ActivityManager
import android.content.Context
import ar.com.instafood.activities.MainActivity


class MenuProductAdapter(val products : ArrayList<Product>) : RecyclerView.Adapter<MenuProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(var card: View) : RecyclerView.ViewHolder(card) {
        init {
            card.setOnClickListener { v: View ->
                var position: Int = adapterPosition
                mContext = v.context

                fragmentJump(card)

                Snackbar.make(v, "Click detectado en posicion $position", Snackbar.LENGTH_LONG).show()
            }
        }

        private val mFragment: ProductDetailFragment ? = ProductDetailFragment()
        private var mBundle: Bundle ? = null
        private var mContext: Context? = null

        private fun fragmentJump(card: View) {
            mBundle = Bundle()
            mBundle?.putString("title","alto title")

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