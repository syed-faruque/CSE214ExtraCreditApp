package com.syedfaruque.talenttrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PrintKeyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_key);

        String key = getIntent().getStringExtra("ENCRYPTION_KEY");
        KeyTable keyTable = KeyTable.buildFromString(key);
        TextView keyTextView = findViewById(R.id.key);
        keyTextView.setText(keyTable.toString());

        Button returnToMenuButton = findViewById(R.id.return_to_menu_button);
        returnToMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(PrintKeyActivity.this, SecondActivity.class);
            intent.putExtra("ENCRYPTION_KEY", key);
            startActivity(intent);
            finish();
        });
    }
}
