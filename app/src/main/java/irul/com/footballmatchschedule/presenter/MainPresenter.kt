package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import irul.com.footballclubv2.CoroutineContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.EspressoIdlingResource
import irul.com.footballmatchschedule.model.EventsItem
import irul.com.footballmatchschedule.model.TeamOfLeague
import irul.com.footballmatchschedule.view.MainView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MainPresenter(private val view: MainView, private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getEventpastList(league_id:String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiService.getEventPast(league_id)),
                        EventsItem::class.java
                )
            }

            val events = data.await().events
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
            view.displayEvent(events)
            view.hideLoading()

        }
    }

    fun getEventnextList(league_id:String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiService.getEventNext(league_id)),
                        EventsItem::class.java
                )
            }

            val events = data.await().events
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
            view.displayEvent(events)
            view.hideLoading()
        }

    }
}