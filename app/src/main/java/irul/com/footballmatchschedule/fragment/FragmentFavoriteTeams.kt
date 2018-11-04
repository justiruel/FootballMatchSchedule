package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import android.support.v7.widget.SearchView
import android.view.*
import irul.com.footballmatchschedule.activity.DetailTeamLeagueActivity
import irul.com.footballmatchschedule.adapter.TeamLeagueAdapter
import irul.com.footballmatchschedule.model.TableTeams
import irul.com.footballmatchschedule.model.TeamOfLeagueDetail
import kotlinx.android.synthetic.main.fragment_favorite_matches.*
import kotlinx.android.synthetic.main.fragment_favorite_teams.*
import org.jetbrains.anko.support.v4.onRefresh

class FragmentFavoriteTeams : Fragment() {
    private var teams: MutableList<TeamOfLeagueDetail> = mutableListOf()
    private lateinit var adapter: TeamLeagueAdapter
    private lateinit var listPlayer: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listPlayer.layoutManager = LinearLayoutManager(activity)

        adapter = TeamLeagueAdapter(activity,teams){
            startActivity<DetailTeamLeagueActivity>("teamOfLeagueDetailData" to it)
        }
        listPlayer.adapter = adapter
        showFavorite()

        swipe_refresh_favorite_teams.onRefresh {
            teams.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            swipe_refresh_favorite_teams.isRefreshing = false //untuk hide loading bar
            val result = select(TableTeams.TABLE_TEAMS)
            val favorite = result.parseList(classParser<TeamOfLeagueDetail>())
            teams.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_teams, container, false)
        listPlayer = rootView.findViewById(R.id.listTeamFavorite)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) //berhubungan dengan searchView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu.size() == 0) {
            inflater.inflate(R.menu.search_menu_bar, menu)
        }
        val item = menu.findItem(R.id.search)
        item.setIcon(R.drawable.ic_action_search) // sets icon
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        val sv = item.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText)
                return false
            }

        })
        item.actionView = sv
    }
}