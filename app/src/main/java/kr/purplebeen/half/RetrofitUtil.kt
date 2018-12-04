package kr.purplebeen.half

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    val retrofit = Retrofit.Builder()
            .baseUrl("http://10.27.2.150:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
