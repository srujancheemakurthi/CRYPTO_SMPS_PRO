package com.example.crypto_smps_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CRYPTO_SMPS extends AppCompatActivity {
    Button enc, dec, abt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r_y_p_t_o__s_m_p_s);

        enc = findViewById(R.id.enc);
        dec = findViewById(R.id.dec);
        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent temp = new Intent(CRYPTO_SMPS.this, Encoder.class);
                startActivity(temp);

            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(CRYPTO_SMPS.this, Decoder.class);
                startActivity(temp);
            }
        });
    }
}