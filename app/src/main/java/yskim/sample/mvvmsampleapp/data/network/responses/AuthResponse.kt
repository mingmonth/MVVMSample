package yskim.sample.mvvmsampleapp.data.network.responses

import yskim.sample.mvvmsampleapp.data.db.entities.User

//{"error":false,"id":4,"email":"testuser@test.com"}
data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String,
    val user: User
//    val id : Int,
//    val username: String,
//    val email: String
)