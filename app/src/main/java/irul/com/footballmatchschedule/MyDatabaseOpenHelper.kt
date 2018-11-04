package irul.com.footballmatchschedule

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import irul.com.footballmatchschedule.model.TableEvents
import irul.com.footballmatchschedule.model.TableTeams
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Events.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TableEvents.TABLE_EVENTS, true,
                TableEvents.ID_EVENT to TEXT + UNIQUE,
                TableEvents.STR_HOME_TEAM to TEXT,
                TableEvents.STR_AWAY_TEAM to TEXT,
                TableEvents.DATE_EVENT to TEXT,
                TableEvents.INT_HOME_SCORE to TEXT,
                TableEvents.INT_AWAY_SCORE to TEXT,
                TableEvents.STR_HOME_GOAL_DETAILS to TEXT,
                TableEvents.STR_AWAY_GOAL_DETAILS to TEXT,
                TableEvents.INT_HOME_SHOTS to TEXT,
                TableEvents.INT_AWAY_SHOTS to TEXT,
                TableEvents.STR_HOME_LINEUP_GOAL_KEEPER to TEXT,
                TableEvents.STR_AWAY_LINEUP_GOAL_KEEPER to TEXT,
                TableEvents.STR_HOME_LINEUP_DEFENSE to TEXT,
                TableEvents.STR_AWAY_LINEUP_DEFENSE to TEXT,
                TableEvents.STR_HOME_LINEUP_MIDFIELD to TEXT,
                TableEvents.STR_AWAY_LINEUP_MIDFIELD to TEXT,
                TableEvents.STR_HOME_LINEUP_FORWARD to TEXT,
                TableEvents.STR_AWAY_LINEUP_FORWARD to TEXT,
                TableEvents.STR_HOME_LINEUP_SUBTITUTES to TEXT,
                TableEvents.STR_AWAY_LINEUP_SUBTITUTES to TEXT,
                TableEvents.STR_EVENT to TEXT
        )

        db.createTable(TableTeams.TABLE_TEAMS, true,
                TableTeams.ID_TEAM to TEXT + UNIQUE,
                TableTeams.STR_DESCRIPTION_EN to TEXT,
                TableTeams.STR_TEAM to TEXT,
                TableTeams.STR_TEAM_BADGE to TEXT,
                TableTeams.INT_FORMED_YEAR to TEXT,
                TableTeams.STR_STADIUM to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TableEvents.TABLE_EVENTS, true)
        db.dropTable(TableTeams.TABLE_TEAMS, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)