package ar.com.instafood.fragments


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.MenuTabsAdapter
import ar.com.instafood.fragments.menuFragments.ProductFragment
import io.socket.client.Socket


class MenuFragment : Fragment() {
    var listFragment = arrayListOf<Fragment>()
    var listTitle = arrayListOf<String>()
    var menuViewPager: ViewPager? = null
    var menuTabLayout: TabLayout? = null
    var menuAdapter : MenuTabsAdapter? = null

    init {
        listFragment.clear()
        listTitle.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_menu, container, false)
        //initialise()
        prepareDataResource()
        setBar(view)

        menuViewPager = view.findViewById(R.id.menuViewPager)
        menuTabLayout = view.findViewById(R.id.menuTabs)


        if(menuAdapter == null) {
            menuAdapter = MenuTabsAdapter(childFragmentManager, listFragment, listTitle)
        }
        menuViewPager!!.adapter = menuAdapter

        menuTabLayout!!.setupWithViewPager(menuViewPager)

        return view
    }

    private fun setBar(view: View) {
        var toolbar = view.findViewById<android.support.v7.widget.Toolbar>(R.id.hbtoolbar)
        toolbar.title = "La Birra Bar"
    }

    private fun initialise() {
        listTitle.clear()
        listFragment.clear()
    }

    private fun prepareDataResource() {
        if (listFragment.isEmpty()) {
            addData(ProductFragment.newInstance("platosPrincipales"), "Platos Principales")
            addData(ProductFragment.newInstance("platosSecundarios"), "Entradas")
            addData(ProductFragment.newInstance("postreBebidas"), "Bebidas y Postres Especiales")
        }
    }

    private fun addData(fragment : Fragment, title : String ){
        listFragment.add(fragment)
        listTitle.add(title)

    }

}
