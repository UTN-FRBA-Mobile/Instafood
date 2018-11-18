package ar.com.instafood.fragments.order

import android.view.View
import android.widget.ImageView
import ar.com.instafood.activities.R

class StarService {


    fun init(view: View) {
        val goldStars : MutableList<ImageView> = mutableListOf()
        goldStars.add(view.findViewById<View>(R.id.img_gold_star_1) as ImageView)
        goldStars.add(view.findViewById<View>(R.id.img_gold_star_2) as ImageView)
        goldStars.add(view.findViewById<View>(R.id.img_gold_star_3) as ImageView)
        goldStars.add(view.findViewById<View>(R.id.img_gold_star_4) as ImageView)
        goldStars.add(view.findViewById<View>(R.id.img_gold_star_5) as ImageView)

        for (star in goldStars) {
            star.setOnClickListener { _ ->
                goldStars.forEach { it.visibility = View.INVISIBLE }
                starsToTurnOn(goldStars, star).forEach { it.visibility = View.VISIBLE }
            }
        }

        val greyStars : MutableList<ImageView> = mutableListOf()
        greyStars.add(view.findViewById<View>(R.id.img_grey_star_1) as ImageView)
        greyStars.add(view.findViewById<View>(R.id.img_grey_star_2) as ImageView)
        greyStars.add(view.findViewById<View>(R.id.img_grey_star_3) as ImageView)
        greyStars.add(view.findViewById<View>(R.id.img_grey_star_4) as ImageView)
        greyStars.add(view.findViewById<View>(R.id.img_grey_star_5) as ImageView)

        for (star in greyStars) {
            star.setOnClickListener { _ ->
                goldStars.forEach { it.visibility = View.INVISIBLE }
                starsToTurnOn(goldStars, star).forEach { it.visibility = View.VISIBLE }
            }
        }

    }

    private fun starsToTurnOn(goldStars :MutableList<ImageView>, selectedStar: ImageView): List<ImageView> {
        val starsToTurnOn = ArrayList<ImageView>()
        val selectedStarIndex = Integer.valueOf(selectedStar.contentDescription.toString())
        for (i in 0 until selectedStarIndex) {
            starsToTurnOn.add(goldStars[i])
        }
        return starsToTurnOn
    }

}