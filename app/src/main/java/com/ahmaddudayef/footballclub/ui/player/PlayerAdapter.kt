package com.ahmaddudayef.footballclub.ui.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_players.view.*

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
class PlayerAdapter(private val players: MutableList<Player>?): RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    interface Callback {
        fun onTeamClick(player: Player)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback){
        this.callback = callback
    }

    fun addItems(players: MutableList<Player>){
        if (players != null){
            this.players?.addAll(players)
            notifyItemRangeInserted(0, players.size)
        }
    }

    fun clearItem(){
        players?.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_players, parent, false))
    }

    override fun getItemCount(): Int {
        return players?.size ?: 0
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players!![position])
    }

    inner class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItem(player: Player){
            itemView.tv_player_title.text = player.strPlayer
            itemView.tv_player_position.text = player.strPosition
            Glide.with(itemView)
                    .load(player.strCutout)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                    .into(itemView.tv_player_image)

            itemView.setOnClickListener{
                callback?.onTeamClick(player)
            }
        }
    }
}