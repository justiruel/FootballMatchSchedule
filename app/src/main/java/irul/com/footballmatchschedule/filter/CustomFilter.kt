package irul.com.footballmatchschedule.filter

import android.widget.Filter
import irul.com.footballmatchschedule.adapter.EventsAdapter
import irul.com.footballmatchschedule.model.EventsItemDetail
import java.util.ArrayList

class CustomFilter(internal var filterList: List<EventsItemDetail>, internal var adapter: EventsAdapter) : Filter() {
    override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
        var constraint = constraint
        val results = Filter.FilterResults()
        if (constraint != null && constraint.length > 0) {
            constraint = constraint.toString().toUpperCase()
            val filteredPlayers = ArrayList<EventsItemDetail>()
            for (i in filterList.indices) {
                if (filterList[i].strEvent.toString().toUpperCase().contains(constraint)) {
                    filteredPlayers.add(filterList[i])
                }
            }

            results.count = filteredPlayers.size
            results.values = filteredPlayers
        } else {
            results.count = filterList.size
            results.values = filterList
        }

        return results
    }

    override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
        adapter.eventspastItems = results.values as ArrayList<EventsItemDetail>
        adapter.notifyDataSetChanged()
    }
}