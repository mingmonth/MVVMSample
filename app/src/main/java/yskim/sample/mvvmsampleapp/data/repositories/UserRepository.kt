package yskim.sample.mvvmsampleapp.data.repositories

import retrofit2.Response
import yskim.sample.mvvmsampleapp.data.db.AppDatabase
import yskim.sample.mvvmsampleapp.data.db.entities.User
import yskim.sample.mvvmsampleapp.data.network.MyApi
import yskim.sample.mvvmsampleapp.data.network.SafeApiRequest
import yskim.sample.mvvmsampleapp.data.network.responses.AuthResponse

class UserRepository(
    private val api : MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

    suspend fun userLogin(email: String, password: String) : AuthResponse {
        return apiRequest { api.userLogin(email, password) }
//    suspend fun userLogin(email: String, password: String) : Response<AuthResponse> {
//        return MyApi().userLogin(email, password)
//        val loginResponse = MutableLiveData<String>()
//
//        MyApi().userLogin(email, password)
//            .enqueue(object: Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    loginResponse.value = t.message
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if(response.isSuccessful) {
//                        loginResponse.value = response.body()?.string()
//                    } else {
//                        loginResponse.value = response.errorBody()?.string()
//                    }
//                }
//            })
//
//        return loginResponse

    }

}