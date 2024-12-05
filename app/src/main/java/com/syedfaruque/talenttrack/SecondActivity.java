package com.syedfaruque.talenttrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String key = getIntent().getStringExtra("ENCRYPTION_KEY");
        TextView keyDisplay = findViewById(R.id.key_display);
        keyDisplay.setText("Key: " + key);

        Button changeKeyButton = findViewById(R.id.change_key_button);
        Button encryptButton = findViewById(R.id.encrypt_button);
        Button decryptButton = findViewById(R.id.decrypt_button);

        changeKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, EncryptionActivity.class);
                intent.putExtra("ENCRYPTION_KEY", key);
                startActivity(intent);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, DecryptionActivity.class);
                intent.putExtra("ENCRYPTION_KEY", key);
                startActivity(intent);
            }
        });
    }
}
