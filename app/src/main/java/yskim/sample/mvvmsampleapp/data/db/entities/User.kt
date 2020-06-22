package yskim.sample.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 1

@Entity
data class User(
    var username: String? = null,
    var password: String? = null,
    var email: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_USER_ID
}