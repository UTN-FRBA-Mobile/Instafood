package ar.com.instafood.fragments.menuFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.MenuProductAdapter
import ar.com.instafood.models.getSampleProducts
import kotlinx.android.synthetic.main.activity_search_restaurants.*
import kotlinx.android.synthetic.main.fragment_dish.*
import kotlinx.android.synthetic.main.fragment_dish.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DishFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_dish, container, false)
        var products = getSampleProducts()
        view.recyclerViewSearchProduct.setHasFixedSize(true)
        view.recyclerViewSearchProduct.layoutManager = LinearLayoutManager(context)
        view.recyclerViewSearchProduct.adapter = MenuProductAdapter(products)
        return view


    }


}
