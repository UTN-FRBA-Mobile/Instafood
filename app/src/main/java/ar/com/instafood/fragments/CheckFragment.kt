package ar.com.instafood.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.adapters.CheckAdapter
import ar.com.instafood.models.Check
import ar.com.instafood.models.getSampleCheck
import kotlinx.android.synthetic.main.fragment_check.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 *
 */
class CheckFragment : Fragment() {

    private var checks: ArrayList<Check>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_check, container, false)
        checks = getSampleCheck()
        view.cardList.setHasFixedSize(true)
        view.cardList.layoutManager = LinearLayoutManager(context)
        view.cardList.adapter = CheckAdapter(checks!!)
        return view
    }

    private fun initialize() {
    }


}
