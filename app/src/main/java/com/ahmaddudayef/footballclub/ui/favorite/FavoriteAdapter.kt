package com.ahmaddudayef.footballclub.ui.favorite

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchItem
import kotlinx.android.synthetic.main.activity_detail.view.*
import org.jetbrains.anko.*

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
class FavoriteAdapter(private var matchFavorit: List<EventsItem>): RecyclerView.Adapter<FavoriteAdapter.FavMatchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavMatchHolder(FavMatchItem().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun getItemCount(): Int = matchFavorit.size

    override fun onBindViewHolder(holder: FavMatchHolder, position: Int) {
        holder.setMatchData(matchFavorit[position])
    }

    inner class FavMatchHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val matchDate: TextView = view.findViewById(ID_DATE)
        private val matchHomeTeam: TextView = view.findViewById(ID_HOME_TEAM)
        private val matchHomeScore: TextView = view.findViewById(ID_HOME_SCORE)
        private val matchAwayTeam: TextView = view.findViewById(ID_AWAY_TEAM)
        private val matchAwayScore: TextView = view.findViewById(ID_AWAY_SCORE)

        fun setMatchData(item: EventsItem) {
            matchDate.text = item.dateEvent
            matchHomeTeam.text = item.strHomeTeam
            matchAwayTeam.text = item.strAwayTeam
            if (item.intHomeScore.equals("null") && item.intAwayScore.equals("null")){
                matchHomeScore.text = ""
                matchAwayScore.text = ""
            } else {
                matchHomeScore.text = item.intHomeScore
                matchAwayScore.text = item.intAwayScore
            }

        }
    }


    companion object {
        const val ID_DATE = 1
        const val ID_HOME_TEAM = 2
        const val ID_HOME_SCORE = 3
        const val ID_AWAY_TEAM = 4
        const val ID_AWAY_SCORE = 5
    }

    inner class FavMatchItem: AnkoComponent<ViewGroup> {

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    backgroundColor = Color.WHITE
                    orientation = LinearLayout.VERTICAL
                    padding = dip(8)

                    textView {
                        id = ID_DATE
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            id = ID_HOME_TEAM
                            gravity = Gravity.CENTER
                            textSize = 18f
                            text = "home"
                        }.lparams(matchParent, wrapContent, 1f)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            textView {
                                id = ID_HOME_SCORE
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                                text = "0"
                            }

                            textView {
                                text = "vs"
                            }

                            textView {
                                id = ID_AWAY_SCORE
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                                text = "0"
                            }
                        }

                        textView {
                            id = ID_AWAY_TEAM
                            gravity = Gravity.CENTER
                            textSize = 18f
                            text = "away"
                        }.lparams(matchParent, wrapContent, 1f)
                    }
                }.lparams(matchParent, matchParent) {
                    setMargins(dip(16), dip(8), dip(16), dip(8))
                }
            }
        }
    }

}