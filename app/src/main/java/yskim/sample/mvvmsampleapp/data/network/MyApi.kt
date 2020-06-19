package yskim.sample.mvvmsampleapp.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import yskim.sample.mvvmsampleapp.data.network.responses.AuthResponse

interface MyApi {

    @FormUrlEncoded
    @POST("userLogin.php")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    companion object {
        operator fun invoke() : MyApi {
            return Retrofit.Builder()
                .baseUrl("http://192.168.30.52/Android/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}

