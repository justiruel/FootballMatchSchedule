package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import org.junit.Test
import irul.com.footballclubv2.TestContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.model.Team
import irul.com.footballmatchschedule.model.TeamDetail
import irul.com.footballmatchschedule.view.DetailView
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.collections.ArrayList

@RunWith(JUnit4::class)
class DetailPresenterTest {
    private lateinit var presenter: DetailPresenter
    @Test
    fun getTeamLogoHome() {
        val teams: ArrayList<TeamDetail> = arrayListOf()
        val teamName = "Southampton"
        val response = Team(teams)
        `when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getTeamLogo(teamName)),
                Team::class.java
        )).thenReturn(response)
        presenter.getTeamLogoHome(teamName)
        verify(view).showImageHome(teams)
    }

    @Test
    fun testToSimpleString() {
        assertEquals("Sat, 15 Jun 1991", presenter.formatDate("1991-06-15"))
    }

    @Mock
    private
    lateinit var view: DetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, TestContextProvider())
    }
}