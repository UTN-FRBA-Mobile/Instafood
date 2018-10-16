package ar.com.instafood.adapters
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.instafood.models.Restaurant
import ar.com.instafood.activities.R
import ar.com.instafood.fragments.SearchRestaurantFragment
import ar.com.instafood.interfaces.adapterCallback
import kotlinx.android.synthetic.main.single_restaurant_card.view.*


class RestaurantAdapter(val restaurants : List<Restaurant>, var cb : adapterCallback) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    init{
        this.cb = cb;
    }


    class RestaurantViewHolder(var card: View, adapterCallback: adapterCallback) : RecyclerView.ViewHolder(card){
        init {
        card.setOnClickListener { v: View ->
            var position : Int = adapterPosition
            //Snackbar.make(v,"Click detectado en posicion $position",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            adapterCallback.onItemClick(position);
            }
        }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        if (holder != null) {
            val restaurant = restaurants[position]
            holder.card.tv_title.text = restaurant.title
            holder.card.tv_description.text = restaurant.description
            holder.card.tv_distance.text = restaurant.distance
            holder.card.iv_icon.setImageResource(restaurant.image)
        }
    }
    //Create a new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_restaurant_card,parent,false)
        return RestaurantViewHolder(view,cb)
    }

    override fun getItemCount() = restaurants.size


}