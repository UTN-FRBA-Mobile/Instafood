package ar.com.instafood.fragments


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.ListFragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.MenuTabsAdapter
import ar.com.instafood.fragments.menuFragments.DishFragment
import ar.com.instafood.fragments.menuFragments.DrinkFragment
import ar.com.instafood.fragments.menuFragments.SnackFragment
import kotlinx.android.synthetic.main.fragment_menu.*

import kotlinx.android.synthetic.main.toolbar.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MenuFragment : Fragment() {
    var listFragment = arrayListOf<Fragment>()
    //var listFragment : ArrayList<Fragment>? = null
    var listTitle = arrayListOf<String>()
    open lateinit var menuViewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initialise()
        prepareDataResource()

        val result = inflater.inflate(R.layout.fragment_menu, container, false)

        var adapter = MenuTabsAdapter(fragmentManager!!, listFragment!!, listTitle!!)
        menuViewPager = result.findViewById(R.id.menuViewPager)
        menuViewPager.adapter = adapter

        return result
    }

    override fun onResume() {
        Log.e("DEBUG", "onResume of LoginFragment")
        super.onResume()
    }

    private fun initialise() {
        activity?.toolbar?.setTitle("InstaFood - Menu")
        //menuViewPager = view!!.findViewById(R.id.menuViewPager)

    }

    private fun prepareDataResource() {
        addData(DishFragment(),"Platos")
        addData(SnackFragment(),"Snacks")
        addData(DrinkFragment(),"Bebidas")
    }

    private fun addData(fragment : Fragment, title : String ){
        listFragment?.add(fragment)
        listTitle?.add(title)

    }

}
