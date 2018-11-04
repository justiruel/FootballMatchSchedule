package irul.com.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TeamOfLeague(
        var teams: ArrayList<TeamOfLeagueDetail>
)