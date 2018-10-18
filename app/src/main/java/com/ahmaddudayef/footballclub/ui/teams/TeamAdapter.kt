package com.ahmaddudayef.footballclub.ui.teams

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
class TeamAdapter(val teams: MutableList<Team>?): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    interface Callback {
        fun onTeamClick(team: Team)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback){
        this.callback = callback
    }

    fun addItems(teams: MutableList<Team>){
        if (teams != null){
            this.teams?.addAll(teams)
            notifyItemRangeInserted(0, teams.size)
        }
    }

    fun clearItem(){
        teams?.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount(): Int {
        return teams?.size ?: 0
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams!![position])
    }

    inner class TeamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItem(team: Team){
            itemView.tv_team_title.text = team.strTeam
            Glide.with(itemView)
                    .load(team.strTeamBadge)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                    .into(itemView.tv_team_image)

            itemView.setOnClickListener{
                callback?.onTeamClick(team)
            }
        }
    }

}