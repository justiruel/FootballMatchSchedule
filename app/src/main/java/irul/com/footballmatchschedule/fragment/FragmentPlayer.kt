package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import com.google.gson.Gson
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.ApiService
import irul.com.footballmatchschedule.EspressoIdlingResource
import irul.com.footballmatchschedule.adapter.EventsAdapter
import irul.com.footballmatchschedule.activity.DetailActivity
import irul.com.footballmatchschedule.presenter.MainPresenter
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.activity.DetailPlayerActivity
import irul.com.footballmatchschedule.activity.DetailTeamLeagueActivity
import irul.com.footballmatchschedule.adapter.PlayerAdapter
import irul.com.footballmatchschedule.adapter.TeamLeagueAdapter
import irul.com.footballmatchschedule.model.*
import irul.com.footballmatchschedule.presenter.PlayerPresenter
import irul.com.footballmatchschedule.presenter.TeamLeaguePresenter
import irul.com.footballmatchschedule.view.MainView
import irul.com.footballmatchschedule.view.PlayerView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FragmentPlayer : Fragment(),PlayerView {

    private var playerArray: MutableList<PlayerDetail> = mutableListOf()
    lateinit var listPlayer:RecyclerView
    private lateinit var swipe_refresh:SwipeRefreshLayout
    lateinit var adapter:PlayerAdapter
    private lateinit var presenter: PlayerPresenter
    lateinit var idTeam:String
    lateinit var progressBar:ProgressBar

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }


    override fun displayEvent(player: Iterable<PlayerDetail>) {
        playerArray.addAll(player)
        listPlayer.layoutManager = LinearLayoutManager(activity)
        adapter = PlayerAdapter(activity, playerArray) {
            startActivity<DetailPlayerActivity>("detailPlayer" to it)
        }
        listPlayer.adapter = adapter

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_player, container, false)

        idTeam =  arguments?.getString("idTeam").toString()

        swipe_refresh = rootView.findViewById(R.id.swipe_refresh)
        listPlayer = rootView.findViewById(R.id.listPlayer)
        progressBar = rootView.findViewById(R.id.progressBar)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this,request,gson)
        showData(idTeam)

        swipe_refresh.onRefresh {
            showData(idTeam)
        }
        return rootView
    }

    private fun showData(idTeam:String){
        swipe_refresh.isRefreshing = false
        playerArray.clear()
        presenter.getPlayerList(idTeam)
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.GONE
    }
}