package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import irul.com.footballclubv2.CoroutineContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.EspressoIdlingResource
import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.model.Team
import irul.com.footballmatchschedule.view.DetailView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.text.SimpleDateFormat

class DetailPresenter(private val view: DetailView, private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamLogoHome(teamName : String?) {
        EspressoIdlingResource.increment()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiService.getTeamLogo(teamName)),
                        Team::class.java
                )
            }

            val teams = data.await().teams
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
            view.showImageHome(teams)
        }
    }

    fun getTeamLogoAway(teamName : String?) {
        EspressoIdlingResource.increment()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiService.getTeamLogo(teamName)),
                        Team::class.java
                )
            }

            val teams = data.await().teams
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
            view.showImageAway(teams)
        }
    }

    fun getAllData(allData : EventsItemDetail) {
        view.showDetailData(allData,formatDate(allData.dateEvent))
    }

    fun formatDate(dateEvent:String?):String?{
        var spf = SimpleDateFormat("yyyy-MM-dd")
        val newDate = spf.parse(dateEvent)
        spf = SimpleDateFormat("EEE, dd MMM yyyy")
        return spf.format(newDate)
    }
}