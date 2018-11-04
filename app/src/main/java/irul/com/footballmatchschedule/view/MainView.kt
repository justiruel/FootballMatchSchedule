package irul.com.footballmatchschedule.view

import irul.com.footballmatchschedule.model.EventsItemDetail

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun displayEvent(events: Iterable<EventsItemDetail>)
}