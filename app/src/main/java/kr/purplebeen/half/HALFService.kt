package kr.purplebeen.half

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HALFService {
    @GET("/users/add_user")
    fun registerDevice(@Query("id") id: String, @Query("name") name: String, @Query("rpi_ip") rpiIp: String, @Query("hmd_ip") hmdIp: String): Call<Result>

    @GET("/query")
    fun requestData(@Query("data") data : String) : Call<String>

}
