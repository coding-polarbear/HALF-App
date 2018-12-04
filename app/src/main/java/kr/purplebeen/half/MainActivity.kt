package kr.purplebeen.half

import androidx.appcompat.app.AppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    private var socket: Socket? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            setSocket()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

    }

    @Throws(URISyntaxException::class)
    private fun setSocket() {
        socket = IO.socket("http://10.27.2.150:3001")
        socket!!.on("result") {
            runOnUiThread {
                Thread.sleep(1000)
                if(it[0].toString() == "dog_bark") {
                    resultImage.setBackgroundResource(R.drawable.dogbark)
                } else if(it[0].toString() == "gun_shot") {
                    resultImage.setBackgroundResource(R.drawable.gunshot)
                } else if(it[0].toString() == "siren") {
                    resultImage.setBackgroundResource(R.drawable.siren)
                } else if(it[0].toString() == "car_horn") {
                    resultImage.setBackgroundResource(R.drawable.carhorn)
                }
                Log.d("asdf", it.toString())
            }

        }
        socket!!.connect()
    }
}
