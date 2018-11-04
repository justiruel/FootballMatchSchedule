package irul.com.footballmatchschedule.view

import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.model.TeamDetail

interface DetailView {
    fun showImageHome(teamDetail:ArrayList<TeamDetail>)
    fun showImageAway(teamDetail:ArrayList<TeamDetail>)
    fun showDetailData(eventsItemDetailData:EventsItemDetail,date:String?)
}