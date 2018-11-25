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
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment() {
    var listFragment = arrayListOf<Fragment>()
    var listTitle = arrayListOf<String>()
    var menuViewPager: ViewPager? = null
    var menuTabLayout: TabLayout? = null
    var menuAdapter : MenuTabsAdapter? = null
    private lateinit var viewMenu : View

    init {
        listFragment.clear()
        listTitle.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewMenu = inflater.inflate(R.layout.fragment_menu, container, false)
        shareMenu()
        //initialise()
        prepareDataResource()
        setBar(viewMenu)

        menuViewPager = viewMenu.findViewById(R.id.menuViewPager)
        menuTabLayout = viewMenu.findViewById(R.id.menuTabs)


        if(menuAdapter == null) {
            menuAdapter = MenuTabsAdapter(childFragmentManager, listFragment, listTitle)
        }
        menuViewPager!!.adapter = menuAdapter

        menuTabLayout!!.setupWithViewPager(menuViewPager)

        return viewMenu
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


    private fun shareMenu(){
        viewMenu.searchImageButton.setOnClickListener { _ ->
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            var showQRFragment = ShowQRFragment()
            transaction.replace(R.id.fragment_container, showQRFragment).addToBackStack(null)
            transaction.commit()
        }
    }


}

