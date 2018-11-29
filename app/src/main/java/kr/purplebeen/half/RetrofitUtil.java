package kr.purplebeen.half;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    public static final Retrofit retrofit  = new Retrofit.Builder()
            .baseUrl("http://10.27.2.150:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
