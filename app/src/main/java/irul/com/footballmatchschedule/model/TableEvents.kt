package irul.com.footballmatchschedule.model

data class TableEvents(
        val idEvent                     : String?,
        val strHomeTeam                 : String?,
        val strAwayTeam                 : String?,
        val dateEvent                   : String?,
        val intHomeScore                : String?,
        val intAwayScore                : String?,
        val strHomeGoalDetails          : String?,
        val strAwayGoalDetails          : String?,
        val intHomeShots                : String?,
        val intAwayShots                : String?,
        val strHomeLineupGoalkeeper     : String?,
        val strAwayLineupGoalkeeper     : String?,
        val strHomeLineupDefense        : String?,
        val strAwayLineupDefense        : String?,
        val strHomeLineupMidfield       : String?,
        val strAwayLineupMidfield       : String?,
        val strHomeLineupForward        : String?,
        val strAwayLineupForward        : String?,
        val strHomeLineupSubstitutes    : String?,
        val strAwayLineupSubstitutes    : String?,
        val strEvent                    : String?
) {
    companion object {
        const val TABLE_EVENTS: String = "TABLE_EVENTS"
        const val ID_EVENT: String = "ID_EVENT"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val INT_HOME_SCORE : String = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE : String = "INT_AWAY_SCORE"
        const val STR_HOME_GOAL_DETAILS : String = "STR_HOME_GOAL_DETAILS"
        const val STR_AWAY_GOAL_DETAILS : String = "STR_AWAY_GOAL_DETAILS"
        const val INT_HOME_SHOTS : String = "INT_HOME_SHOTS"
        const val INT_AWAY_SHOTS : String = "INT_AWAY_SHOTS"
        const val STR_HOME_LINEUP_GOAL_KEEPER : String = "STR_HOME_LINEUP_GOAL_KEEPER"
        const val STR_AWAY_LINEUP_GOAL_KEEPER : String = "STR_AWAY_LINEUP_GOAL_KEEPER"
        const val STR_HOME_LINEUP_DEFENSE : String = "STR_HOME_LINEUP_DEFENSE"
        const val STR_AWAY_LINEUP_DEFENSE : String = "STR_AWAY_LINEUP_DEFENSE"
        const val STR_HOME_LINEUP_MIDFIELD : String = "STR_HOME_LINEUP_MIDFIELD"
        const val STR_AWAY_LINEUP_MIDFIELD : String = "STR_AWAY_LINEUP_MIDFIELD"
        const val STR_HOME_LINEUP_FORWARD : String = "STR_HOME_LINEUP_FORWARD"
        const val STR_AWAY_LINEUP_FORWARD : String = "STR_AWAY_LINEUP_FORWARD"
        const val STR_HOME_LINEUP_SUBTITUTES : String = "STR_HOME_LINEUP_SUBTITUTES"
        const val STR_AWAY_LINEUP_SUBTITUTES : String = "STR_AWAY_LINEUP_SUBTITUTES"
        const val STR_EVENT : String = "STR_EVENT"

    }
}