package kr.purplebeen.half

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.telephony.TelephonyManager



class IncomingCallBroadcastReceiver : BroadcastReceiver() {

    var mLastState : String = ""
    companion object {
        val TAG : String = "PHONE STATE"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive()")
        val state = intent!!.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (state == mLastState) {
            return
        } else {
            mLastState = state
        }


        if(TelephonyManager.EXTRA_STATE_RINGING == state) {
            var incomingNumber : String = intent!!.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            val phoneNumber : String = PhoneNumberUtils.formatNumber(incomingNumber)
            Log.d("phone_number", phoneNumber)
            var serviceIntent : Intent = Intent(context, CallingService::class.java)
            serviceIntent.putExtra(CallingService.EXTRA_CALL_NUMBER, phoneNumber)
            context!!.startService(serviceIntent)
        }
    }
}