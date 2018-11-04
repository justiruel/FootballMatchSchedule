package irul.com.footballmatchschedule.view

import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.model.Player
import irul.com.footballmatchschedule.model.PlayerDetail

interface PlayerView {
    fun displayEvent(player: Iterable<PlayerDetail>)
    fun showLoading()
    fun hideLoading()
}