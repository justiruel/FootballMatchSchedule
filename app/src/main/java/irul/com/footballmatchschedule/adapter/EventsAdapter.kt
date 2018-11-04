package irul.com.footballmatchschedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.R
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import android.widget.Filter
import irul.com.footballmatchschedule.filter.CustomFilter


class EventsAdapter(private val context: Context?,  var eventspastItems: List<EventsItemDetail>, private val listener: (EventsItemDetail) -> Unit)
    : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private var eventspastItemsFiltered : List<EventsItemDetail> = eventspastItems
    private var filter: CustomFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(eventspastItems[position],listener)
    }

    override fun getItemCount(): Int = eventspastItems.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        fun bindItem(items: EventsItemDetail, listener: (EventsItemDetail) -> Unit) {
            itemView.strHomeTeam.text = items.strHomeTeam
            itemView.strAwayTeam.text = items.strAwayTeam

            var date = items.dateEvent
            var spf = SimpleDateFormat("yyyy-MM-dd")
            val newDate = spf.parse(date)
            spf = SimpleDateFormat("EEE, dd MMM yyyy")
            date = spf.format(newDate)

            itemView.dateEvent.text = date
            itemView.intHomeScore.text = items.intHomeScore
            itemView.intAwayScore.text = items.intAwayScore
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    fun getFilter(): Filter? {
        filter = CustomFilter(eventspastItemsFiltered, this)
        return filter
    }
}