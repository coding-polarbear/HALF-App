package kr.purplebeen.half

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.telephony.SmsMessage
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class SmsReceiver : BroadcastReceiver() {

    companion object {
        val SMS_RECEIVED : String = "android.provider.Telephony.SMS_RECEIVED"
        val TAG : String = "SmsReceiver"
        val format : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    }


    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // Bundle을 이용해서 메세지 내용을 가져옴
        val bundle = intent.extras
        val messages = parseSmsMessage(bundle)
        // 메세지가 있을 경우 내용을 로그로 출력해 봄
        if (messages.size > 0) {
            // 메세지의 내용을 가져옴
            val sender = messages[0]!!.originatingAddress
            val contents = messages[0]!!.messageBody.toString()
            val receivedDate = Date(messages[0]!!.timestampMillis)

            // 로그를 찍어보는 과정이므로 생략해도 됨
            Log.d(TAG, "Sender :" + sender!!)
            Log.d(TAG, "contents : $contents")
            Log.d(TAG, "receivedDate : $receivedDate")

            var halfService : HALFService = RetrofitUtil.retrofit.create(HALFService::class.java)
            var call = halfService.requestData("format:message from ${PhoneNumberUtils.formatNumber(sender)}")
            call.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("error", t.toString())
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.e("success", response.body().toString())
                }

            })
        }

     }

    fun parseSmsMessage(bundle : Bundle) : Array<SmsMessage?> {
        var objs  = bundle.get("pdus") as Array<Object>
        var messages : Array<SmsMessage?> = Array(objs.size){null}

        for(i in 0 until objs.size) {
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                var format : String = bundle.getString("format")!!
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }

        return messages
    }
}
