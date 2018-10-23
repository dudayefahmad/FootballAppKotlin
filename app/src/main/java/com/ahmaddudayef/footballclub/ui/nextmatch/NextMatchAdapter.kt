package com.ahmaddudayef.footballclub.ui.nextmatch

import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.utils.ReminderUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class NextMatchAdapter(private val events: MutableList<EventsItem>?):
        RecyclerView.Adapter<NextMatchAdapter.NextMatchHolder>() {

    interface Callback {
        fun onNextMatchClick(eventsItem: EventsItem)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback){
        this.callback = callback
    }

    fun addItems(events: MutableList<EventsItem>?){
        if (events != null){
            this.events?.addAll(events)
            notifyItemRangeInserted(0, events.size)
        }
    }

    fun clearItems() {
        events?.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return events?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchHolder {
        val ui = AnkoContext.create(parent.context, parent)
        val nextMatchListItem = NextMatchItem()
        val view = nextMatchListItem.createView(ui)

        return NextMatchHolder(view, nextMatchListItem)
    }

    override fun onBindViewHolder(holder: NextMatchHolder, position: Int) {
        holder.bindItem(events!![position])
    }

    inner class NextMatchHolder(val view: View, private val nextMatchItem: NextMatchItem): RecyclerView.ViewHolder(view){
        fun bindItem(item: EventsItem) {
            nextMatchItem.txtDateNext.text = item.dateEvent
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val dateMatch = sdf.parse(item.dateEvent) as Date
            val dateNow = sdf.parse(sdf.format(Calendar.getInstance().time))

            if (dateMatch > dateNow || dateMatch == dateNow) {
                nextMatchItem.imgReminder.setOnClickListener {
                    Toast.makeText(view.context, "Optional Feature :)", Toast.LENGTH_LONG).show()
                }
            }

            Glide.with(view)
                    .load(R.drawable.ic_notifications_active_black_24dp)
                    .into(nextMatchItem.imgReminder)

            nextMatchItem.txtTimeNext.text = item.strTime.substring(0, 5)
            nextMatchItem.txtHomeTeamNext.text = item.strHomeTeam
            nextMatchItem.txtAwayTeamNext.text = item.strAwayTeam
            if (item.intHomeScore.equals("null") && item.intAwayScore.equals("null")){
                nextMatchItem.txtHomeScoreNext.text = ""
                nextMatchItem.txtAwayScoreNext.text = ""
            } else {
                nextMatchItem.txtHomeScoreNext.text = item.intHomeScore
                nextMatchItem.txtAwayScoreNext.text = item.intAwayScore
            }
            itemView.setOnClickListener {
                callback?.onNextMatchClick(item)
            }
        }
    }

}