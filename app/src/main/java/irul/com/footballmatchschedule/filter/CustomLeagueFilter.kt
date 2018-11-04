package irul.com.footballmatchschedule.filter

import android.widget.Filter
import irul.com.footballmatchschedule.adapter.TeamLeagueAdapter
import irul.com.footballmatchschedule.model.TeamOfLeagueDetail
import java.util.ArrayList

class CustomLeagueFilter(internal var filterList: List<TeamOfLeagueDetail>, internal var adapter: TeamLeagueAdapter) : Filter() {
    override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
        var constraint = constraint
        val results = Filter.FilterResults()
        if (constraint != null && constraint.length > 0) {
            constraint = constraint.toString().toUpperCase()
            val filteredPlayers = ArrayList<TeamOfLeagueDetail>()
            for (i in filterList.indices) {
                if (filterList[i].strTeam.toString().toUpperCase().contains(constraint)) {
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
        adapter.eventspastItems = results.values as ArrayList<TeamOfLeagueDetail>
        adapter.notifyDataSetChanged()
    }
}