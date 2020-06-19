package yskim.sample.mvvmsampleapp.data.repositories

import retrofit2.Response
import yskim.sample.mvvmsampleapp.data.network.MyApi
import yskim.sample.mvvmsampleapp.data.network.responses.AuthResponse

class UserRepository {

    suspend fun userLogin(email: String, password: String) : Response<AuthResponse> {
        return MyApi().userLogin(email, password)
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