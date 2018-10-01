package ar.com.instafood.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.activities.R
import ar.com.instafood.models.Check
import kotlinx.android.synthetic.main.check_card.view.*

class CheckAdapter(val checks: ArrayList<Check>) : RecyclerView.Adapter<CheckAdapter.CheckViewHolder>() {

    class CheckViewHolder(val card: View): RecyclerView.ViewHolder(card)

    override fun onBindViewHolder(holder: CheckViewHolder, p1: Int) {
        if (holder != null) {
            val check = checks[p1]
            holder.card.title.text = check.name
            holder.card.txtProduct.text = check.products.first().title
            holder.card.txtCantidad.text = 1.toString()
            holder.card.txtTotal.text = check.products.first().price.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CheckViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.check_card,parent,false)
        return CheckViewHolder(view)
    }

    override fun getItemCount(): Int = checks.size

}