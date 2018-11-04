package irul.com.footballmatchschedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import irul.com.footballmatchschedule.R
import kotlinx.android.synthetic.main.item_player.view.*
import com.squareup.picasso.Picasso
import irul.com.footballmatchschedule.model.PlayerDetail

class PlayerAdapter(private val context: Context?, var playerItems: List<PlayerDetail>, private val listener: (PlayerDetail) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(playerItems[position],listener)
    }

    override fun getItemCount(): Int = playerItems.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        fun bindItem(items: PlayerDetail, listener: (PlayerDetail) -> Unit) {
            Picasso.get().load(items.strCutout).into(itemView.image)
            itemView.name.text =  items.strPlayer
            itemView.position.text =  items.strPosition
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}