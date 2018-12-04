package kr.purplebeen.half

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.33:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
