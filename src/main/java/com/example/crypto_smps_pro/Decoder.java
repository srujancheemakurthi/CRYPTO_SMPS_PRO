package com.example.crypto_smps_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Decoder extends AppCompatActivity {
    EditText etdec;
    TextView dectv;
    ClipboardManager cplboard;  // It is used for copy purpose

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);  etdec = findViewById(R.id.etdec);
        dectv = findViewById(R.id.dectv);
        cplboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }
    //passing the value to the Encode

    public void dec (View view) {
        String temp = etdec.getText().toString();
        String rv = decode.dec(temp);
        dectv.setText(rv);
    }

    // for coping the values
    public void cp1 (View view) {
        String data = dectv.getText().toString().trim();
        if (!data.isEmpty()) {
            ClipData temp = ClipData.newPlainText("text", data);
            cplboard.setPrimaryClip(temp);
            Toast.makeText(this, "Copied", Toast.LENGTH_LONG).show();
        }

    }
}