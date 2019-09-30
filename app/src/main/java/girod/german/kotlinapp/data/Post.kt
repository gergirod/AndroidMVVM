package girod.german.kotlinapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(val userId : Int, val id : Int, val title : String, val body : String) :Parcelable {
}