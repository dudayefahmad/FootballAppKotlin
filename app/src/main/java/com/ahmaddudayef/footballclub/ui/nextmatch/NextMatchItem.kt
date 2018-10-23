package com.ahmaddudayef.footballclub.ui.nextmatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ahmaddudayef.footballclub.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class NextMatchItem: AnkoComponent<ViewGroup> {

    lateinit var txtDateNext: TextView
    lateinit var imgReminder: ImageView
    lateinit var txtTimeNext: TextView
    lateinit var txtHomeTeamNext: TextView
    lateinit var txtHomeScoreNext: TextView
    lateinit var txtAwayTeamNext: TextView
    lateinit var txtAwayScoreNext: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL

            linearLayout {
                backgroundColor = Color.WHITE
                orientation = LinearLayout.VERTICAL
                padding = dip(8)

                relativeLayout{
                    txtDateNext = textView {
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }.lparams(wrapContent, wrapContent) {
                        centerHorizontally()
                    }

                    imgReminder = imageView {

                    }.lparams(width = dip(30), height = dip(30)) {
                        alignParentRight()
                    }
                }

                txtTimeNext = textView {
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL

                    txtHomeTeamNext = textView {
                        gravity = Gravity.CENTER
                        textSize = 18f
                        text = "home"
                    }.lparams(matchParent, wrapContent, 1f)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        txtHomeScoreNext = textView {
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }

                        textView {
                            text = "vs"
                        }

                        txtAwayScoreNext = textView {
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }
                    }

                    txtAwayTeamNext = textView {
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