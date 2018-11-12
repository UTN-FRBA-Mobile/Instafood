package ar.com.instafood.fragments.menuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ar.com.instafood.activities.R
import ar.com.instafood.application.SocketApplication
import ar.com.instafood.fragments.MenuFragment
import ar.com.instafood.models.Check
import ar.com.instafood.models.Product
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
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
        Picasso.get().load(product_image_string).into(view.iv_image)
        view.toolbar_product_detail.title = tv_title
        view.toolbar_product_detail.navigationIcon = context?.getDrawable(R.drawable.abc_ic_ab_back_material)
        view.toolbar_product_detail.setNavigationOnClickListener({ item ->
            val fragment = fragmentManager!!.findFragmentByTag("MENU_FRAGMENT") as MenuFragment
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment, "MENU_FRAGMENT");
            transaction.addToBackStack(null);
            transaction.commit();
        })
        val app  = activity?.application as SocketApplication
        val gsonBuilder = GsonBuilder().create()
        val socket = app.socket
        val btn = view.findViewById<Button>(R.id.buttonflat)
        val json = gsonBuilder.toJson(Check("Juan", arrayListOf(Product(tv_title ?: "", tv_description ?: "", tv_price?.toInt() ?: 0, product_image_string!!))))

        btn?.setOnClickListener{
            socket?.emit("productSelected",  json)
            val fragment = fragmentManager!!.findFragmentByTag("MENU_FRAGMENT") as MenuFragment
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment, "MENU_FRAGMENT");
            transaction.addToBackStack(null);
            transaction.commit();
        }
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