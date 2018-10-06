package ar.com.instafood.fragments.menuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.models.Product

class ProductDetailFragment : Fragment() {

    private var productList: String? = null
    private var products: ArrayList<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productList = it.getString("title")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.single_product_detail, container, false)


        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(product: String) =
                ProductDetailFragment().apply {
                    arguments = Bundle().apply {
                       putString("title", product)
                    }
                }
    }


}