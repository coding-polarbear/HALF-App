package kr.purplebeen.half;

import androidx.appcompat.app.AppCompatActivity;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, DeviceRegisterActivity.class));

        init();
        try {
            Log.d("asdf", "asdf");
            setSocket();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        resultText = findViewById(R.id.resultText);
    }

    private void setSocket() throws URISyntaxException {
        Log.e("asdf2", "asdf");
        socket = IO.socket("http://10.27.2.150:3001");
        socket.on("result", (Emitter.Listener) args -> {
            runOnUiThread(() -> {
                resultText.setText(args[0].toString());
                Log.d("asdf", args[0].toString());
            });
        });
        socket.connect();
    }
}
