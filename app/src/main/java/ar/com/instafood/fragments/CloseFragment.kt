package ar.com.instafood.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ar.com.instafood.activities.R
import ar.com.instafood.fragments.order.StarService
import android.widget.Toast
import ar.com.instafood.fragments.order.PreferenceUtils


class CloseFragment : Fragment() {

    private lateinit var viewOrder : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.viewOrder = inflater.inflate(R.layout.fragment_close, container, false)
        return this.viewOrder
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PreferenceUtils.setScoreLocked(false, this.requireContext())
        initCloseButton()
        super.onResume()
    }

    private fun initCloseButton() {
        val starService = StarService()
        starService.init(this.view!!, this.requireContext())

        val closeButton = view?.findViewById<Button>(R.id.btn_close_score)
        closeButton?.setOnClickListener { _ ->
            Toast.makeText(getActivity(), "Gracias por puntuar", Toast.LENGTH_LONG).show();
            val label = view?.findViewById<TextView>(R.id.lbl_close_score_description)
            PreferenceUtils.setScoreLocked(true, this.requireContext())
            val scoreDescription = label?.text
            label?.text = "Puntuado: " + scoreDescription
            closeButton.visibility = View.INVISIBLE
        }
    }


}