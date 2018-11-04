package irul.com.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamOfLeagueDetail(
    var idTeam              : String?,
    var strDescriptionEN    : String?,
    var strTeam             : String?,
    var strTeamBadge        : String?,
    var intFormedYear       : String?,
    var strStadium          : String?
): Parcelable