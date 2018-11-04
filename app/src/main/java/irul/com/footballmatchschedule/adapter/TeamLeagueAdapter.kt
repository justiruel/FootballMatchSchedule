package irul.com.footballmatchschedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.Filter
import irul.com.footballmatchschedule.R
import kotlinx.android.synthetic.main.item_league.view.*
import com.squareup.picasso.Picasso
import irul.com.footballmatchschedule.filter.CustomLeagueFilter
import irul.com.footballmatchschedule.model.TeamOfLeagueDetail

class TeamLeagueAdapter(private val context: Context?, var eventspastItems: List<TeamOfLeagueDetail>, private val listener: (TeamOfLeagueDetail) -> Unit)
    : RecyclerView.Adapter<TeamLeagueAdapter.ViewHolder>() {

    private var eventspastItemsFiltered : List<TeamOfLeagueDetail> = eventspastItems
    private var filter: CustomLeagueFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(eventspastItems[position],listener)
    }

    override fun getItemCount(): Int = eventspastItems.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        fun bindItem(items: TeamOfLeagueDetail, listener: (TeamOfLeagueDetail) -> Unit) {
            itemView.text.text = items.strTeam
            Picasso.get().load(items.strTeamBadge).into(itemView.image)
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    fun getFilter(): Filter? {
        filter = CustomLeagueFilter(eventspastItemsFiltered, this)
        return filter
    }
}