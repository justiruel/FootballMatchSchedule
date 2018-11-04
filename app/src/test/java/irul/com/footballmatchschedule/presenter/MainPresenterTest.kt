package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import irul.com.footballmatchschedule.model.EventsItemDetail
import org.junit.Test
import irul.com.footballclubv2.TestContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.model.EventsItem
import irul.com.footballmatchschedule.view.MainView
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.collections.ArrayList

@RunWith(JUnit4::class)
class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    @Test
    fun getEventpastList() {
        val events: ArrayList<EventsItemDetail> = arrayListOf()
        val response = EventsItem(events)
        val league_id = "4328"
        `when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getEventPast(league_id)),
                EventsItem::class.java
        )).thenReturn(response)
        presenter.getEventpastList(league_id)
        verify(view).showLoading()
        verify(view).displayEvent(events)
        verify(view).hideLoading()
    }

    @Test
    fun getEventnextList() {
        val events: ArrayList<EventsItemDetail> = arrayListOf()
        val response = EventsItem(events)
        val league_id = "4328"
        `when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getEventNext(league_id)),
                EventsItem::class.java
        )).thenReturn(response)
        presenter.getEventpastList(league_id)
        verify(view).showLoading()
        verify(view).displayEvent(events)
        verify(view).hideLoading()
    }

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }
}