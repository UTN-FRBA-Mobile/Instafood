package ar.com.instafood.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ar.com.instafood.activities.R
import ar.com.instafood.fragments.order.StarService
import android.widget.Toast
import android.R.attr.data



class CloseFragment : Fragment() {

    private lateinit var viewOrder : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.viewOrder = inflater.inflate(R.layout.fragment_close, container, false)
        return this.viewOrder
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCloseButton()
        super.onResume()
    }

    private fun initCloseButton() {
        val starService = StarService()
        starService.init(this.view!!, this.requireContext())

        val closeButton = view?.findViewById<Button>(R.id.btn_close_score)
        closeButton?.setOnClickListener { _ ->
            Toast.makeText(getActivity(), "This is my Toast message!", Toast.LENGTH_LONG).show();
            closeButton.visibility = View.INVISIBLE
        }
    }


}