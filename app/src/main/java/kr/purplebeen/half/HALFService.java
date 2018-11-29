package kr.purplebeen.half;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HALFService {
    @GET("/users/add_user")
    Call<Result> registerDevice(@Query("id") String id, @Query("name") String name, @Query("rpi_ip") String rpiIp, @Query("hmd_ip") String hmdIp);
}
