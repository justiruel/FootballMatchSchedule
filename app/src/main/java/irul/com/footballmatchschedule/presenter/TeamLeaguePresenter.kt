package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import irul.com.footballclubv2.CoroutineContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.EspressoIdlingResource
import irul.com.footballmatchschedule.model.EventsItem
import irul.com.footballmatchschedule.model.TeamOfLeague
import irul.com.footballmatchschedule.view.MainView
import irul.com.footballmatchschedule.view.TeamLeagueView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamLeaguePresenter(private val view: TeamLeagueView, private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamOfLeagueList(league:String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiService.getTeams(league)),
                        TeamOfLeague::class.java
                )
            }

            val teams = data.await().teams
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
            view.displayEvent(teams)
            view.hideLoading()
        }

    }
}
