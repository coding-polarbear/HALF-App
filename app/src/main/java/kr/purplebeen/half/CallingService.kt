package kr.purplebeen.half

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CallingService : Service(){
    var call_number : String = ""
    companion object {
        val EXTRA_CALL_NUMBER : String = "call_number"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(EXTRA_CALL_NUMBER,  call_number)
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        setExtra(intent)
        return Service.START_REDELIVER_INTENT
    }


    private fun setExtra(intent: Intent?) {
        call_number = intent!!.getStringExtra(EXTRA_CALL_NUMBER)
        Log.e(EXTRA_CALL_NUMBER,  call_number)
        var halfService : HALFService = RetrofitUtil.retrofit.create(HALFService::class.java)
        var call  = halfService.requestData("phone from $call_number")
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("error", t.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("asdf", response.body().toString())
            }

        })
    }
}