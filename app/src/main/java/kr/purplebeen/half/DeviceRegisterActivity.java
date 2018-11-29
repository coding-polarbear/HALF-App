package kr.purplebeen.half;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DeviceRegisterActivity extends AppCompatActivity {
    private TextInputEditText editId;
    private TextInputEditText editName;
    private TextInputEditText editRpiIp;
    private TextInputEditText editHmdIp;
    private MaterialButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_register);
        init();
        setListeners();
    }

    private void init() {
        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editRpiIp = findViewById(R.id.editRpiIp);
        editHmdIp = findViewById(R.id.editHmdIp);
        registerButton = findViewById(R.id.registerButton);
    }

    private void setListeners() {
        registerButton.setOnClickListener(v -> {
            HALFService halfService = RetrofitUtil.retrofit.create(HALFService.class);
            Call<Result> call = halfService.registerDevice(
                    editId.getText().toString(),
                    editName.getText().toString(),
                    editRpiIp.getText().toString(),
                    editHmdIp.getText().toString());
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    switch(response.code()) {
                        case 200:
                            Toast.makeText(getApplicationContext(), "기기등록에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        case 500:
                            Toast.makeText(getApplicationContext(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                            break;
                        case 203:
                            Toast.makeText(getApplicationContext(), "Already Exist!", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.e("asdf2", t.getMessage());
                }
            });
        });
    }
}
