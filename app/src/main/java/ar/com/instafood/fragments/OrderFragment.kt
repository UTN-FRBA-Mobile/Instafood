package ar.com.instafood.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ar.com.instafood.activities.R


class OrderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchSearchRestaurants()
    }

    private fun switchSearchRestaurants() {
        val restSearch = getView()?.findViewById<Button>(R.id.btn_close_table)
        restSearch?.setOnClickListener { _ ->
            val amount = view?.findViewById<TextView>(R.id.txt_final_amount)
            val amountFormat = this.resources.getString(R.string.final_amount)
            amount?.text = amountFormat + "808.30"
        }
    }



}
