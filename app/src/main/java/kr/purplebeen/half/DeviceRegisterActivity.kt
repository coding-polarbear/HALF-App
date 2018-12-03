package kr.purplebeen.half

import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_device_register.*

class DeviceRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_register)
        setListeners()
    }



    private fun setListeners() {
        registerButton!!.setOnClickListener { v ->
            val halfService = RetrofitUtil.retrofit.create<HALFService>(HALFService::class.java!!)
            val call = halfService.registerDevice(
                    editId!!.text!!.toString(),
                    editName!!.text!!.toString(),
                    editRpiIp!!.text!!.toString(),
                    editHmdIp!!.text!!.toString())
            call.enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    when (response.code()) {
                        200 -> {
                            Toast.makeText(applicationContext, "기기등록에 성공하였습니다!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        }
                        500 -> Toast.makeText(applicationContext, "Internal Server Error!", Toast.LENGTH_SHORT).show()
                        203 -> Toast.makeText(applicationContext, "Already Exist!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.e("asdf2", t.message)
                }
            })
        }
    }
}
