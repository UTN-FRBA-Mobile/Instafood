package ar.com.instafood.fragments.menuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.MenuProductAdapter
import ar.com.instafood.models.Product
import kotlinx.android.synthetic.main.fragment_drink.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SnackFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SnackFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SnackFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_snack, container, false)
        var product = Product("Papas con chedart","Descripcion: papas - panceta - chedart",140, R.drawable.burger180)
        product.addNewOne(Product("albondigas","Descripcion: carne con salsa",50, R.drawable.burger180))
        product.addNewOne(Product("salchichitas","Descripcion: salchis",150, R.drawable.burger180))
        product.addNewOne(Product("tabla de jamones","Descripcion: jamoncito",200, R.drawable.burger180))
        product.addNewOne(Product("bastones de muzzarella","Descripcion: bastoncitos de queso",120, R.drawable.burger180))
        view.recyclerViewSearchProduct.setHasFixedSize(true)
        view.recyclerViewSearchProduct.layoutManager = LinearLayoutManager(context)
        view.recyclerViewSearchProduct.adapter = MenuProductAdapter(product.getProducList())
        return view
    }
}
