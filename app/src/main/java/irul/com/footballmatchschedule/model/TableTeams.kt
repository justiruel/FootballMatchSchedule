package irul.com.footballmatchschedule.model
data class TableTeams(
        val idTeam              : String?,
        val strDescriptionEN    : String?,
        val strTeam             : String?,
        val strTeamBadge        : String?,
        val intFormedYear       : String?,
        val strStadium          : String?
) {
    companion object {
        const val TABLE_TEAMS: String = "TABLE_TEAMS"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_DESCRIPTION_EN: String = "STR_DESCRIPTION_EN"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val INT_FORMED_YEAR : String = "INT_FORMED_YEAR"
        const val STR_STADIUM : String = "STR_STADIUM"
    }
}