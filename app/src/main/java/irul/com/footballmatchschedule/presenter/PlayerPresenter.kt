package irul.com.footballmatchschedule.presenter

import android.util.Log
import com.google.gson.Gson
import irul.com.footballclubv2.CoroutineContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.EspressoIdlingResource
import irul.com.footballmatchschedule.model.EventsItem
import irul.com.footballmatchschedule.model.Player
import irul.com.footballmatchschedule.model.TeamOfLeague
import irul.com.footballmatchschedule.view.MainView
import irul.com.footballmatchschedule.view.PlayerView
import irul.com.footballmatchschedule.view.TeamLeagueView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView, private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(team:String?) {
        EspressoIdlingResource.increment()

        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiService.getPlayer(team)),
                        Player::class.java
                )
            }


            val player = data.await().player
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }

            view.hideLoading()
            view.displayEvent(player)
        }

    }
}
