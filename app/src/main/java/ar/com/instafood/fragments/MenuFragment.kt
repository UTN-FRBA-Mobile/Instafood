package ar.com.instafood.fragments


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
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
import kotlinx.android.synthetic.main.toolbar.*

class MenuFragment : Fragment() {
    var listFragment = arrayListOf<Fragment>()
    var listTitle = arrayListOf<String>()
    open lateinit var menuViewPager: ViewPager
    open lateinit var menuTabLayout: TabLayout

    init {
        listFragment?.clear()
        listTitle?.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initialise()
        prepareDataResource()

        val result = inflater.inflate(R.layout.fragment_menu, container, false)
        menuViewPager = result.findViewById(R.id.menuViewPager)
        menuTabLayout = result.findViewById(R.id.menuTabs)

        menuTabLayout.setupWithViewPager(menuViewPager)
        var adapter = MenuTabsAdapter(fragmentManager!!, listFragment, listTitle)
        menuViewPager.adapter = adapter

        return result
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        menuTabLayout.setupWithViewPager(menuViewPager)
        var adapter = MenuTabsAdapter(fragmentManager!!, listFragment, listTitle)
        menuViewPager.adapter = adapter
    }


    override fun onResume() {
        Log.e("DEBUG", "onResume of LoginFragment")
        super.onResume()
    }

    private fun initialise() {
        activity?.toolbar?.setTitle("InstaFood - Menu")
        listTitle?.clear()
        listFragment?.clear()
    }

    private fun prepareDataResource() {
        addData(DishFragment(),"Platos Principales")
        addData(SnackFragment(),"Platos Secundarios")
        addData(DrinkFragment(),"Postres y Bebidas")
    }

    private fun addData(fragment : Fragment, title : String ){
        listFragment.add(fragment)
        listTitle.add(title)

    }

}
