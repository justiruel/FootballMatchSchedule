package irul.com.footballmatchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.adapter.MainFragmentPagerAdapter
import irul.com.footballmatchschedule.database
import irul.com.footballmatchschedule.fragment.FragmentOverview
import irul.com.footballmatchschedule.fragment.FragmentPlayer
import irul.com.footballmatchschedule.model.TableTeams
import kotlinx.android.synthetic.main.activity_detail_team_league.*
import irul.com.footballmatchschedule.model.TeamOfLeagueDetail
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar

class DetailTeamLeagueActivity : AppCompatActivity() {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    lateinit var teamOfLeagueDetail:TeamOfLeagueDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team_league)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        teamOfLeagueDetail = intent.getParcelableExtra("teamOfLeagueDetailData")
        Picasso.get().load(teamOfLeagueDetail.strTeamBadge).into(teamLogo)
        teamName.text = teamOfLeagueDetail.strTeam
        formedYear.text = teamOfLeagueDetail.intFormedYear
        stadiumName.text = teamOfLeagueDetail.strStadium

        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val mainFragmentPagerAdapter = MainFragmentPagerAdapter(supportFragmentManager,tabLayout.tabCount)

        val bundle = Bundle()
        bundle.putString("description", teamOfLeagueDetail.strDescriptionEN)
        val fragmentOverview = FragmentOverview()
        fragmentOverview.arguments = bundle

        val bundle2 = Bundle()
        bundle2.putString("idTeam", teamOfLeagueDetail.idTeam)
        val fragmentPlayer = FragmentPlayer()
        fragmentPlayer.arguments = bundle2

        mainFragmentPagerAdapter.addFragment(fragmentOverview, getString(R.string.Overview))
        mainFragmentPagerAdapter.addFragment(fragmentPlayer, getString(R.string.Player))

        viewPager.adapter = mainFragmentPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED //TabLayout.MODE_SCROLLABLE
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

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(TableTeams.TABLE_TEAMS,
                        TableTeams.ID_TEAM to teamOfLeagueDetail.idTeam,
                        TableTeams.STR_DESCRIPTION_EN to teamOfLeagueDetail.strDescriptionEN,
                        TableTeams.STR_TEAM to teamOfLeagueDetail.strTeam,
                        TableTeams.STR_TEAM_BADGE to teamOfLeagueDetail.strTeamBadge,
                        TableTeams.INT_FORMED_YEAR to teamOfLeagueDetail.intFormedYear,
                        TableTeams.STR_STADIUM to teamOfLeagueDetail.strStadium
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
                delete(TableTeams.TABLE_TEAMS, "(ID_TEAM = ?)", arrayOf(teamOfLeagueDetail.idTeam))
            }

            snackbar(container, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(container, e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        val idTeam = teamOfLeagueDetail.idTeam?:"-"
        database.use {
            val result = select(TableTeams.TABLE_TEAMS)
                    .whereArgs("(ID_TEAM = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<TableTeams>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}