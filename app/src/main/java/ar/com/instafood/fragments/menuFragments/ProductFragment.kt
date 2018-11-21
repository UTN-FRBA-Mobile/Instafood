package ar.com.instafood.fragments.menuFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import ar.com.instafood.activities.R
import ar.com.instafood.activities.R.id.container
import ar.com.instafood.activities.R.id.recyclerViewSearchProduct
import ar.com.instafood.adapters.MenuProductAdapter
import ar.com.instafood.interfaces.MenuService
import ar.com.instafood.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_product.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PRODUCT_LIST = "productList"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var productList: String? = null
    private var products: ArrayList<Product>? = null
    private var mainProducts : List<Product>? = null
    private var disposable: Disposable? = null
    private var adapter : MenuProductAdapter? = null
    private var menuView : View? = null
    private val menuAPIServe by lazy {
        MenuService.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productList = it.getString(PRODUCT_LIST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_product, container, false)
        menuView = view
        when (productList) {
            "platosPrincipales" -> { products = getMainProducts() }
            "platosSecundarios" -> { products = getSecondaryProducts() }
            "postreBebidas" -> { products = getDrinkProducts() }
            else -> { // Note the block
                Log.d("Error in ProductFrag","no se cargo el menu en el product fragment.")
            }
        }
        view.recyclerViewSearchProduct.setHasFixedSize(true)
        view.recyclerViewSearchProduct.layoutManager = LinearLayoutManager(context)
        view.recyclerViewSearchProduct.adapter = MenuProductAdapter(products!!)
        return view
    }


    private fun getMenu() {
        if (menuView!!.recyclerViewSearchProduct is RecyclerView) {
            with(view) {
                adapter = MenuProductAdapter(products!!)
                view!!.recyclerViewSearchProduct!!.adapter = adapter
                disposable = menuAPIServe.getMenu().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> adapter!!.items = result.mainProducts()
                                    adapter!!.notifyDataSetChanged()
                                    if(result.mainProducts() !== null){
                                        mainProducts = result.mainProducts()
                                    }},
                                { error -> Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show() }
                        )
            }
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(product: String) =
                ProductFragment().apply {
                    arguments = Bundle().apply {
                        putString(PRODUCT_LIST, product)
                    }
                }
    }
}
