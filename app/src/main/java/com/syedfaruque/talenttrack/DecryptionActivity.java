package com.syedfaruque.talenttrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DecryptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
        String key = getIntent().getStringExtra("ENCRYPTION_KEY");

        EditText decryptionInput = findViewById(R.id.decryption_input);
        Button decryptButton = findViewById(R.id.decrypt_submit_button);
        Button returnToMenuButton = findViewById(R.id.return_to_menu_button);

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToDecrypt = decryptionInput.getText().toString().trim();

                if (textToDecrypt.isEmpty()) {
                    Toast.makeText(DecryptionActivity.this, "Please enter text to decrypt", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(DecryptionActivity.this, DecryptionResultsActivity.class);
                    intent.putExtra("ENCRYPTION_KEY", key);
                    intent.putExtra("TEXT_TO_DECRYPT", textToDecrypt);
                    startActivity(intent);
                    finish();
                }
            }
        });

        returnToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DecryptionActivity.this, SecondActivity.class);
                intent.putExtra("ENCRYPTION_KEY", key);
                startActivity(intent);
                finish();
            }
        });
    }
}
