package irul.com.footballmatchschedule.view

import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.model.TeamOfLeagueDetail

interface TeamLeagueView {
    fun showLoading()
    fun hideLoading()
    fun displayEvent(teams: Iterable<TeamOfLeagueDetail>)
}