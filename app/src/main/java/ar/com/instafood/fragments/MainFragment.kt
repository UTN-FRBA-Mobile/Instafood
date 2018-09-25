package ar.com.instafood.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ar.com.instafood.activities.R
import ar.com.instafood.activities.SearchRestaurantsActivity
import kotlinx.android.synthetic.main.toolbar.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initialise()

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun initialise() {
        activity?.toolbar?.setTitle("InstaFood")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get reference to button
        val btn_QRScan = getView()?.findViewById<Button>(R.id.QRScan)
        val btn_restaurantSearch = getView()?.findViewById<Button>(R.id.restaurantSearch)
        // set on-click listener
        btn_QRScan?.setOnClickListener { _ ->

        }
        btn_restaurantSearch?.setOnClickListener { _ ->

        }
        switchSearchRestaurants()
    }

    private fun switchSearchRestaurants() {
        val restSearch = getView()?.findViewById<Button>(R.id.restaurantSearch)
        restSearch?.setOnClickListener { _ ->
            startActivity(Intent(activity, SearchRestaurantsActivity::class.java));
        }
    }

}
