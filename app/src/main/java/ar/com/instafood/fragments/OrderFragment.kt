package ar.com.instafood.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ar.com.instafood.activities.R
import ar.com.instafood.fragments.order.PreferenceUtils
import ar.com.instafood.fragments.order.TimerState
import kotlinx.android.synthetic.main.fragment_order.view.*

class OrderFragment : Fragment() {

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.STOPPED
    private lateinit var viewOrder : View
    private var secondsRemaining: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.viewOrder = inflater.inflate(R.layout.fragment_order, container, false)
        initTimer()
        if (timerState != TimerState.RUNNING) {
            startTimer()
        }
        return this.viewOrder
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initMessage()
        initLabel()
        initOrderDetail()
    }

    override fun onResume() {
        super.onResume()
        //initTimer()
    }

    override fun onPause() {
        super.onPause()
        PreferenceUtils.setTimerState(timerState, this.requireContext())
    }

    private fun initTimer(){
        timerState = PreferenceUtils.getTimerState(this.requireContext())

        setNewTimerLength()

        if (secondsRemaining <= 0)
            onTimerFinished()

        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.STOPPED

        //setNewTimerLength()

        viewOrder.bar_timer.progress = 0

        secondsRemaining = timerLengthSeconds

        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.RUNNING


        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val lengthInMinutes = PreferenceUtils.getTimerLength(this.requireContext())
        timerLengthSeconds = (lengthInMinutes * 60L)
        viewOrder.bar_timer.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        viewOrder.lbl_timer?.text = "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        viewOrder.bar_timer?.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun initToolbar() {
        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_restaurant)
        toolbar?.title = "En Mesa"
    }

    private fun initMessage() {
        val amount = view?.findViewById<TextView>(R.id.lbl_timer_description)
        amount?.text = this.resources.getString(R.string.countdown_message)
    }

    private fun initLabel() {
        val amount = view?.findViewById<TextView>(R.id.lbl_last_order)
        amount?.text = this.resources.getString(R.string.tu_ultimo_pedido)
    }

    private fun initOrderDetail() {
        val amount = view?.findViewById<TextView>(R.id.lbl_order_detail)
        amount?.text = "\t\tDetalle\n\t\t\t- 1 Hamburguesa Doble Queso\n" +
                "\t\t\t- 2 Cocacola 500ml\n" +
                "\t\t\t- 1 Papas grandes"
    }
/*
    private fun switchSearchRestaurants() {
        val restSearch = getView()?.findViewById<Button>(R.id.btn_close_table)
        restSearch?.setOnClickListener { _ ->
            val amount = view?.findViewById<TextView>(R.id.txt_final_amount)
            val amountFormat = this.resources.getString(R.string.final_amount)
            amount?.text = amountFormat + "808.30"
        }
    }
*/
}
