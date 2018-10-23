package com.ahmaddudayef.footballclub.ui.favoriteplayer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.ui.detailplayer.DetailPlayerActivity
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_players.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
class FavoritePlayerAdapter(private var players: List<Player>): RecyclerView.Adapter<FavoritePlayerAdapter.FavPlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavPlayerHolder {
        val viewHolder = FavPlayerHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_players, parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent.context.startActivity<DetailPlayerActivity>("player_detail" to players[position])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: FavPlayerHolder, position: Int) {
        holder.bindItem(players[position])
    }

    inner class FavPlayerHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItem(player: Player){
            itemView.tv_player_title.text = player.strPlayer
            itemView.tv_player_position.text = player.strPosition
            Glide.with(itemView)
                    .load(player.strCutout)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                    .into(itemView.tv_player_image)
        }
    }
}