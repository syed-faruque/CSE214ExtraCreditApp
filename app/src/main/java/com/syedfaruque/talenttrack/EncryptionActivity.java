package com.syedfaruque.talenttrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EncryptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        String key = getIntent().getStringExtra("ENCRYPTION_KEY");

        EditText encryptionInput = findViewById(R.id.encryption_input);
        Button encryptButton = findViewById(R.id.encrypt_submit_button);
        Button returnToMenuButton = findViewById(R.id.return_to_menu_button);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToEncrypt = encryptionInput.getText().toString().trim();

                if (textToEncrypt.isEmpty()) {
                    Toast.makeText(EncryptionActivity.this, "Please enter text to encrypt", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(EncryptionActivity.this, EncryptionResultsActivity.class);
                    intent.putExtra("ENCRYPTION_KEY", key);
                    intent.putExtra("TEXT_TO_ENCRYPT", textToEncrypt);
                    startActivity(intent);
                    finish();
                }
            }
        });

        returnToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EncryptionActivity.this, SecondActivity.class);
                intent.putExtra("ENCRYPTION_KEY", key);
                startActivity(intent);
                finish();
            }
        });
    }
}
