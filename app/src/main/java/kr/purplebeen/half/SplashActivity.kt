package kr.purplebeen.half

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Thread(Runnable {
            Thread.sleep(1000)
            var intent: Intent = Intent(this, DeviceRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }).start()

    }
}
