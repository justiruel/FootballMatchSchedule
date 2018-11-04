package irul.com.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerDetail(
        var strCutout           : String?,
        var strPlayer           : String?,
        var strPosition         : String?,
        var strFanart1            : String?,
        var strHeight           : String?,
        var strWeight           : String?,
        var strDescriptionEN    : String?
): Parcelable