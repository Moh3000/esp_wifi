    package com.example.esp_wifi;

    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    public class MainActivity extends AppCompatActivity {
        Button button;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ipUrl = "http://192.168.1.50/";  // replace with your IP and port
                    String message = "Relay_toggled";
                    System.out.println("b");
                    new ConnectionAsyncTask(MainActivity.this).execute(ipUrl, message);
                    System.out.println("f");
                }
            });
        }
    }