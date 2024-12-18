package com.syedfaruque.talenttrack;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText keyInput = findViewById(R.id.key_input);
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = keyInput.getText().toString().replaceAll("[^a-zA-Z]", "");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("ENCRYPTION_KEY", key);
                startActivity(intent);
            }
        });
    }
}
