package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import org.junit.Test
import irul.com.footballclubv2.TestContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.model.*
import irul.com.footballmatchschedule.view.TeamLeagueView
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.collections.ArrayList

@RunWith(JUnit4::class)
class TeamLeaguePresenterTest {
    private lateinit var presenter: TeamLeaguePresenter
    @Test
    fun getTeamOfLeagueList() {
        val team: ArrayList<TeamOfLeagueDetail> = arrayListOf()
        val response = TeamOfLeague(team)
        val league = "English Premier League"
        `when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getTeams(league)),
                TeamOfLeague::class.java
        )).thenReturn(response)
        presenter.getTeamOfLeagueList(league)
        verify(view).showLoading()
        verify(view).displayEvent(team)
        verify(view).hideLoading()
    }

    @Mock
    private
    lateinit var view: TeamLeagueView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }
}