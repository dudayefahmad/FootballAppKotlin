package com.ahmaddudayef.footballclub.ui.prevmatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ahmaddudayef.footballclub.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Ahmad Dudayef on 9/18/2018.
 */
class PrevMatchItem: AnkoComponent<ViewGroup> {

    lateinit var txtDatePrev: TextView
    lateinit var txtHomeTeamPrev: TextView
    lateinit var txtHomeScorePrev: TextView
    lateinit var txtAwayTeamPrev: TextView
    lateinit var txtAwayScorePrev: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL

            linearLayout {
                backgroundColor = Color.WHITE
                orientation = LinearLayout.VERTICAL
                padding = dip(8)

                txtDatePrev = textView {
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL

                    txtHomeTeamPrev = textView {
                        gravity = Gravity.CENTER
                        textSize = 18f
                        text = "home"
                    }.lparams(matchParent, wrapContent, 1f)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        txtHomeScorePrev = textView {
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }

                        textView {
                            text = "vs"
                        }

                        txtAwayScorePrev = textView {
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }
                    }

                    txtAwayTeamPrev = textView {
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