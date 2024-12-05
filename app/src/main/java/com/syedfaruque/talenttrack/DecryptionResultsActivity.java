package com.syedfaruque.talenttrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DecryptionResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption_results);

        String key = getIntent().getStringExtra("ENCRYPTION_KEY");
        String textToDecrypt = getIntent().getStringExtra("TEXT_TO_DECRYPT");

        KeyTable keyTable = KeyTable.buildFromString(key);
        Phrase phrase = Phrase.buildPhraseFromStringForEnc(textToDecrypt);
        Phrase decryptedPhrase = phrase.decrypt(keyTable);

        TextView decryptedPhraseTextView = findViewById(R.id.decrypted_phrase);
        decryptedPhraseTextView.setText(decryptedPhrase.toString());

        Button returnToMenuButton = findViewById(R.id.return_to_menu_button);
        returnToMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(DecryptionResultsActivity.this, SecondActivity.class);
            intent.putExtra("ENCRYPTION_KEY", key);
            startActivity(intent);
            finish();
        });
    }
}
