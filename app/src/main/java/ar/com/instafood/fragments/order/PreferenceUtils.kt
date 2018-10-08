package ar.com.instafood.fragments.order

import android.content.Context
import android.preference.PreferenceManager
import ar.com.instafood.fragments.OrderFragment

class PreferenceUtils {

    companion object {

        private const val TIMER_STATE_ID = "com.resocoder.timer.timer_state"

        fun getTimerLength(context: Context): Int{
            return 1
        }

        fun getTimerState(context: Context): TimerState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return TimerState.values()[ordinal]
        }

        fun setTimerState(state: TimerState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

    }

}