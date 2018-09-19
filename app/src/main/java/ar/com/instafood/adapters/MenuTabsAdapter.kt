package ar.com.instafood.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

open class MenuTabsAdapter(fm : FragmentManager, fragmentlist : ArrayList<Fragment>, titleList: ArrayList<String>) : FragmentPagerAdapter(fm) {
    var listFragment : ArrayList<Fragment>? = null
    var listTitle : ArrayList<String>? = null

    init {
        listFragment = fragmentlist
        listTitle = titleList
    }

    override fun getCount(): Int {
        return listFragment!!.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment!!.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle!!.get(position)
    }

}