package com.ahmaddudayef.footballclub.ui.favoriteteam

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamActivity
import com.ahmaddudayef.footballclub.ui.favoritematch.FavoriteMatchAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
class FavoriteTeamAdapter(private var teamFavorite: List<Team>): RecyclerView.Adapter<FavoriteTeamAdapter.FavTeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamHolder {
        val viewHolder = FavTeamHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teams, parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent.context.startActivity<DetailTeamActivity>("team_detail" to teamFavorite[position])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = teamFavorite.size

    override fun onBindViewHolder(holder: FavTeamHolder, position: Int) {
        holder.bindItem(teamFavorite[position])
    }

    inner class FavTeamHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItem(team: Team){
            itemView.tv_team_title.text = team.strTeam
            Glide.with(itemView)
                    .load(team.strTeamBadge)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                    .into(itemView.tv_team_image)
        }
    }
}