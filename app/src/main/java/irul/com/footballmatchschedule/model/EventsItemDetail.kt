package irul.com.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItemDetail(
        var idEvent                     : String?,
        var strHomeTeam                 : String?,
        var strAwayTeam                 : String?,
        var dateEvent                   : String?,
        var intHomeScore                : String?,
        var intAwayScore                : String?,
        var strHomeGoalDetails          : String?,
        var strAwayGoalDetails          : String?,
        var intHomeShots                : String?,
        var intAwayShots                : String?,
        var strHomeLineupGoalkeeper     : String?,
        var strAwayLineupGoalkeeper     : String?,
        var strHomeLineupDefense        : String?,
        var strAwayLineupDefense        : String?,
        var strHomeLineupMidfield       : String?,
        var strAwayLineupMidfield       : String?,
        var strHomeLineupForward        : String?,
        var strAwayLineupForward        : String?,
        var strHomeLineupSubstitutes    : String?,
        var strAwayLineupSubstitutes    : String?,
        var strEvent                    : String?
):Parcelable