package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.adapter.EventsAdapter
import irul.com.footballmatchschedule.activity.DetailActivity
import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.presenter.MainPresenter
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.view.MainView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FragmentEventNext : Fragment(),MainView {
    private lateinit var presenter: MainPresenter
    private var eventsNextItems: MutableList<EventsItemDetail> = mutableListOf()
    private lateinit var loader: ProgressBar
    private lateinit var listEventNext: RecyclerView
    private lateinit var swipe_refresh: SwipeRefreshLayout
    private var adapter:EventsAdapter? = null
    lateinit var league_id:String

    override fun displayEvent(events: Iterable<EventsItemDetail>) {
        eventsNextItems.addAll(events)
        listEventNext.layoutManager = LinearLayoutManager(activity)

        adapter = EventsAdapter(activity, eventsNextItems) {
            startActivity<DetailActivity>("eventsItemDetailData" to it)
        }

        listEventNext.adapter = adapter
    }

    override fun showLoading() {
        loader.show()
    }

    override fun hideLoading() {
        loader.hide()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_event_next, container, false)

        loader = rootView.findViewById(R.id.loader)
        listEventNext = rootView.findViewById(R.id.listEventNext)
        swipe_refresh = rootView.findViewById(R.id.swipe_refresh)

        var spinnerMatch = rootView.findViewById<Spinner>(R.id.spinner_match_next)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getIntArray(R.array.league_id)
        val spinnerAdapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerMatch.adapter = spinnerAdapter


        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this,request,gson)
        spinnerMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                league_id = spinnerItemsId[position].toString()
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
        eventsNextItems.clear()
        presenter.getEventnextList(league_id)
    }


    private fun View.show() {
        this.visibility = View.VISIBLE
    }
    private fun View.hide() {
        this.visibility = View.GONE
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

}