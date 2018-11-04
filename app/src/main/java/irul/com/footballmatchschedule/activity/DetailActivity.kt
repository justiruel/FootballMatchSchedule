package irul.com.footballmatchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import irul.com.footballmatchschedule.ApiRepository
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.database
import irul.com.footballmatchschedule.model.EventsItemDetail
import irul.com.footballmatchschedule.model.TableEvents
import irul.com.footballmatchschedule.model.TeamDetail
import irul.com.footballmatchschedule.presenter.DetailPresenter
import irul.com.footballmatchschedule.view.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar

class DetailActivity : AppCompatActivity(),DetailView {
    private lateinit var presenter: DetailPresenter
    private lateinit var eventsItemDetailData:EventsItemDetail
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun showDetailData(eventsItemDetailData: EventsItemDetail,date:String?) {
        dateEvent.text = date
        strHomeTeam.text = eventsItemDetailData.strHomeTeam
        strAwayTeam.text = eventsItemDetailData.strAwayTeam
        intHomeScore.text = eventsItemDetailData.intHomeScore
        intAwayScore.text = eventsItemDetailData.intAwayScore
        strHomeLineupGoalkeeper.text = eventsItemDetailData.strHomeLineupGoalkeeper
        strAwayLineupGoalkeeper.text = eventsItemDetailData.strAwayLineupGoalkeeper
        intHomeShots.text = eventsItemDetailData.intHomeShots
        intAwayShots.text = eventsItemDetailData.intAwayShots
        strHomeGoalDetails.text = eventsItemDetailData.strHomeGoalDetails
        strAwayGoalDetails.text = eventsItemDetailData.strAwayGoalDetails
        strHomeLineupDefense.text = eventsItemDetailData.strHomeLineupDefense
        strAwayLineupDefense.text = eventsItemDetailData.strAwayLineupDefense
        strHomeLineupMidfield.text = eventsItemDetailData.strHomeLineupMidfield
        strAwayLineupMidfield.text = eventsItemDetailData.strAwayLineupMidfield
        strHomeLineupForward.text = eventsItemDetailData.strHomeLineupForward
        strAwayLineupForward.text = eventsItemDetailData.strAwayLineupForward
        strHomeLineupSubstitutes.text = eventsItemDetailData.strHomeLineupSubstitutes
        strAwayLineupSubstitutes.text = eventsItemDetailData.strAwayLineupSubstitutes

        presenter.getTeamLogoHome(eventsItemDetailData.strHomeTeam)
        presenter.getTeamLogoAway(eventsItemDetailData.strAwayTeam)
    }

    override fun showImageHome(teamDetail: ArrayList<TeamDetail>) {
        Picasso.get().load(teamDetail[0].strTeamBadge).into(strTeamBadgeHome)
    }

    override fun showImageAway(teamDetail:ArrayList<TeamDetail>) {
        Picasso.get().load(teamDetail[0].strTeamBadge).into(strTeamBadgeAway)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        eventsItemDetailData = intent.getParcelableExtra("eventsItemDetailData")
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this,request,gson)
        presenter.getAllData(eventsItemDetailData)
        favoriteState()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(TableEvents.TABLE_EVENTS,
                        TableEvents.ID_EVENT to eventsItemDetailData.idEvent,
                        TableEvents.STR_HOME_TEAM to eventsItemDetailData.strHomeTeam ,
                        TableEvents.STR_AWAY_TEAM to eventsItemDetailData.strAwayTeam,
                        TableEvents.DATE_EVENT to eventsItemDetailData.dateEvent,
                        TableEvents.INT_HOME_SCORE to eventsItemDetailData.intHomeScore,
                        TableEvents.INT_AWAY_SCORE to eventsItemDetailData.intAwayScore,
                        TableEvents.STR_HOME_GOAL_DETAILS to eventsItemDetailData.strHomeGoalDetails,
                        TableEvents.STR_AWAY_GOAL_DETAILS to eventsItemDetailData.strAwayGoalDetails,
                        TableEvents.INT_HOME_SHOTS to eventsItemDetailData.intHomeShots,
                        TableEvents.INT_AWAY_SHOTS to eventsItemDetailData.intAwayShots,
                        TableEvents.STR_HOME_LINEUP_GOAL_KEEPER to eventsItemDetailData.strHomeLineupGoalkeeper,
                        TableEvents.STR_AWAY_LINEUP_GOAL_KEEPER to eventsItemDetailData.strAwayLineupGoalkeeper,
                        TableEvents.STR_HOME_LINEUP_DEFENSE to eventsItemDetailData.strHomeLineupDefense,
                        TableEvents.STR_AWAY_LINEUP_DEFENSE to eventsItemDetailData.strAwayLineupDefense,
                        TableEvents.STR_HOME_LINEUP_MIDFIELD to eventsItemDetailData.strHomeLineupMidfield,
                        TableEvents.STR_AWAY_LINEUP_MIDFIELD to eventsItemDetailData.strAwayLineupMidfield,
                        TableEvents.STR_HOME_LINEUP_FORWARD to eventsItemDetailData.strHomeLineupForward,
                        TableEvents.STR_AWAY_LINEUP_FORWARD to eventsItemDetailData.strAwayLineupForward,
                        TableEvents.STR_HOME_LINEUP_SUBTITUTES to eventsItemDetailData.strHomeLineupSubstitutes,
                        TableEvents.STR_AWAY_LINEUP_SUBTITUTES to eventsItemDetailData.strAwayLineupSubstitutes,
                        TableEvents.STR_EVENT to eventsItemDetailData.strEvent
                       )
            }
            snackbar(container, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(container, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(TableEvents.TABLE_EVENTS, "(ID_EVENT = ?)", arrayOf(eventsItemDetailData.idEvent))
            }

            snackbar(container, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(container, e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        val idEvent = eventsItemDetailData.idEvent?:"-"
        database.use {
            val result = select(TableEvents.TABLE_EVENTS)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<TableEvents>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}