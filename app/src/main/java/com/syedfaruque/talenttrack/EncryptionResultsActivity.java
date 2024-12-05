package com.syedfaruque.talenttrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EncryptionResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption_results);

        String key = getIntent().getStringExtra("ENCRYPTION_KEY");
        String textToEncrypt = getIntent().getStringExtra("TEXT_TO_ENCRYPT");

        KeyTable keyTable = KeyTable.buildFromString(key);
        Phrase phrase = Phrase.buildPhraseFromStringForEnc(textToEncrypt);
        Phrase encryptedPhrase = phrase.encrypt(keyTable);

        TextView encryptedPhraseDisplay = findViewById(R.id.encrypted_phrase);

        encryptedPhraseDisplay.setText(encryptedPhrase.toString());

        Button returnToMenuButton = findViewById(R.id.return_to_menu_button);
        returnToMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(EncryptionResultsActivity.this, SecondActivity.class);
            intent.putExtra("ENCRYPTION_KEY", key);
            startActivity(intent);
            finish();
        });
    }
}
