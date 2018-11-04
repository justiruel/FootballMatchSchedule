package irul.com.footballmatchschedule

object ApiService {

    fun getEventPast(league_id:String?): String {
        return BuildConfig.BASE_URL + "eventspastleague.php?id=${league_id}"
    }
    fun getEventNext(league_id:String?): String {
        return BuildConfig.BASE_URL + "eventsnextleague.php?id=${league_id}"
    }
    fun getTeamLogo(t: String?): String {
        return BuildConfig.BASE_URL + "searchteams.php?t=${t}"
    }

    fun getTeams(league:String?):String{
        return BuildConfig.BASE_URL + "search_all_teams.php?l=${league}"
    }

    fun getPlayer(idTeam:String?):String{
        return BuildConfig.BASE_URL + "lookup_all_players.php?id=${idTeam}"
    }
}