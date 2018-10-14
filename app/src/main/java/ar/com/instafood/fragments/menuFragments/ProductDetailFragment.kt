package ar.com.instafood.fragments.menuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import kotlinx.android.synthetic.main.single_product_detail.view.*

class ProductDetailFragment : Fragment() {

    private var tv_title: String? = null
    private var tv_description: String? = null
    private var tv_price: String? = null
    private var product_image_string: String? = null
    private var product_image: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tv_title = it.getString("title")
            tv_description = it.getString("description")
            tv_price = it.getString("price")
            product_image = it.getInt("image")
            product_image_string = it.getString("image_string")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.single_product_detail, container, false)
        view.tv_title.text = tv_title
        view.tv_description.text = tv_description
        view.tv_price.text = tv_price
        view.iv_image.setImageResource(product_image_string!!.toInt())
        view.toolbar_product_detail.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_material));
        view.toolbar_product_detail.setNavigationOnClickListener({ item ->
            //TODO: back action in detail product
        });
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