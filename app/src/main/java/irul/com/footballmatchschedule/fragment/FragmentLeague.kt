package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import irul.com.footballmatchschedule.*
import irul.com.footballmatchschedule.activity.DetailTeamLeagueActivity
import irul.com.footballmatchschedule.adapter.TeamLeagueAdapter
import irul.com.footballmatchschedule.model.TeamOfLeagueDetail
import irul.com.footballmatchschedule.presenter.TeamLeaguePresenter
import irul.com.footballmatchschedule.view.TeamLeagueView
import kotlinx.android.synthetic.main.fragment_league.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FragmentLeague : Fragment(),TeamLeagueView {
    private var teamLeague: MutableList<TeamOfLeagueDetail> = mutableListOf()
    private lateinit var presenter: TeamLeaguePresenter
    lateinit var listTeamLeague:RecyclerView
    lateinit var swipe_refresh:SwipeRefreshLayout

    override fun showLoading() {
        loader.show()
    }

    override fun hideLoading() {
        loader.hide()
    }

    override fun displayEvent(teams: Iterable<TeamOfLeagueDetail>) {
        teamLeague.addAll(teams)
        listTeamLeague.layoutManager = LinearLayoutManager(activity)

        adapter = TeamLeagueAdapter(activity,teamLeague){
            startActivity<DetailTeamLeagueActivity>("teamOfLeagueDetailData" to it)
        }
        listTeamLeague.adapter = adapter
    }

    lateinit var adapter:TeamLeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_league, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        listTeamLeague = rootView.findViewById(R.id.listTeamLeague)

        val loader = rootView.findViewById<ProgressBar>(R.id.loader)
        var spinnerLeague = rootView.findViewById<Spinner>(R.id.spinnerLeague)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerLeague.adapter = spinnerAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamLeaguePresenter(this,request,gson)

        swipe_refresh = rootView.findViewById(R.id.swipe_refresh)

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                showData()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        swipe_refresh.onRefresh {
            showData()
        }
        return rootView
    }

    fun showData(){
        swipe_refresh.isRefreshing = false
        teamLeague.clear()
        presenter.getTeamOfLeagueList(spinnerLeague.selectedItem.toString())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) //berhubungan dengan searchView
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu.size() == 0){
            inflater.inflate(R.menu.search_menu_bar, menu)
        }
        val item = menu.findItem(R.id.search)
        item.setIcon(R.drawable.ic_action_search) // sets icon
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        val sv = item.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
               adapter?.getFilter()?.filter(newText)
                return false
            }

        })
        item.actionView = sv

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun View.show() {
        this.visibility = View.VISIBLE
    }
    private fun View.hide() {
        this.visibility = View.GONE
    }

}