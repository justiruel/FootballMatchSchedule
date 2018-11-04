package irul.com.footballmatchschedule.presenter

import com.google.gson.Gson
import irul.com.footballmatchschedule.model.EventsItemDetail
import org.junit.Test
import irul.com.footballclubv2.TestContextProvider
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.EspressoIdlingResource
import irul.com.footballmatchschedule.model.EventsItem
import irul.com.footballmatchschedule.model.Player
import irul.com.footballmatchschedule.model.PlayerDetail
import irul.com.footballmatchschedule.view.MainView
import irul.com.footballmatchschedule.view.PlayerView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.collections.ArrayList

@RunWith(JUnit4::class)
class PlayerPresenterTest {
    private lateinit var presenter: PlayerPresenter
    @Test
    fun getPlayerList() {
        val player: ArrayList<PlayerDetail> = arrayListOf()
        val response = Player(player)
        val idTeam = "133604"
        `when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getPlayer(idTeam)),
                Player::class.java
        )).thenReturn(response)
        presenter.getPlayerList(idTeam)
        verify(view).showLoading()
        verify(view).displayEvent(player)
        verify(view).hideLoading()
    }

    @Mock
    private
    lateinit var view: PlayerView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }
}