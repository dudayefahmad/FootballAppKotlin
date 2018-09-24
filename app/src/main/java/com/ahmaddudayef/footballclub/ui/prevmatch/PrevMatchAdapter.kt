package com.ahmaddudayef.footballclub.ui.prevmatch

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import org.jetbrains.anko.AnkoContext

/**
 * Created by Ahmad Dudayef on 9/18/2018.
 */
class PrevMatchAdapter(private val events: MutableList<EventsItem>?):
        RecyclerView.Adapter<PrevMatchAdapter.PrevMatchHolder>() {

    interface Callback {
        fun onMatchClick(eventsItem: EventsItem)
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

    override fun getItemCount(): Int {
        return events?.size ?: 0
    }

    private fun getItem(position: Int): EventsItem? {
        return if (position != RecyclerView.NO_POSITION)
            events?.get(position)
        else
            null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchHolder {
        val ui = AnkoContext.create(parent.context, parent)
        val prevMatchListItem = PrevMatchItem()
        val view = prevMatchListItem.createView(ui)

        return PrevMatchHolder(view, prevMatchListItem)
    }

    override fun onBindViewHolder(holder: PrevMatchHolder, position: Int) {
        holder.bindItem(position)
    }


    inner class PrevMatchHolder(val view: View, private val prevMatchItem: PrevMatchItem): RecyclerView.ViewHolder(view){
        fun bindItem(position: Int) {
            val item: EventsItem? = events?.get(position)
            if (item != null){
                prevMatchItem.txtDatePrev.text = item.dateEvent
                prevMatchItem.txtHomeTeamPrev.text = item.strHomeTeam
                prevMatchItem.txtAwayTeamPrev.text = item.strAwayTeam
                if (item.intHomeScore.equals("null") && item.intAwayScore.equals("null")){
                    prevMatchItem.txtHomeScorePrev.text = ""
                    prevMatchItem.txtAwayScorePrev.text = ""
                } else {
                    prevMatchItem.txtHomeScorePrev.text = item.intHomeScore
                    prevMatchItem.txtAwayScorePrev.text = item.intAwayScore
                }
                itemView.setOnClickListener {
                    val event = getItem(adapterPosition)
                    if (event != null){
                        callback?.onMatchClick(event)
                    }
                }
            }
        }
    }
}