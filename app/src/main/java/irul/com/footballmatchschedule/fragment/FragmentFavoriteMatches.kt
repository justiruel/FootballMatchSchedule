package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import irul.com.footballmatchschedule.activity.DetailActivity
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.adapter.EventsAdapter
import irul.com.footballmatchschedule.database
import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.model.TableEvents
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.SearchView
import android.view.*
import kotlinx.android.synthetic.main.fragment_favorite_matches.*
import org.jetbrains.anko.support.v4.onRefresh

class FragmentFavoriteMatches : Fragment() {
    private var events: MutableList<EventsItemDetail> = mutableListOf()
    private lateinit var adapter: EventsAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listEvent.layoutManager = LinearLayoutManager(activity)

        adapter = EventsAdapter(activity,events){
            startActivity<DetailActivity>("eventsItemDetailData" to it)
        }
        listEvent.adapter = adapter
        showFavorite()

        swipe_refresh_favorite_matches.onRefresh {
            events.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            swipe_refresh_favorite_matches.isRefreshing = false //untuk hide loading bar
            val result = select(TableEvents.TABLE_EVENTS)
            val favorite = result.parseList(classParser<EventsItemDetail>())
            events.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_matches, container, false)
        listEvent = rootView.findViewById(R.id.listEventFavorite)
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