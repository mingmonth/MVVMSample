package yskim.sample.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
{
id: 4,
quote: "Never regret anything that made you smile",
author: "Mark Twain",
thumbnail: "https://www.simplifiedcoding.net//demos//marvel//ironman.jpg",
created_at: null,
updated_at: null
},
*/

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val quote: String,
    val author: String,
    val thumbnail: String,
    val created_at: String?,
    val updated_at: String?
)