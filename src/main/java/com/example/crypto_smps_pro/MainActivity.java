package com.example.crypto_smps_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Button Cipher_Cipher,DES_ALGO ,CRYPTO_SMPS,Steganography ;
    ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // A singled code to set the screen to full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        setContentView(R.layout.activity_main);
        CRYPTO_SMPS =findViewById(R.id.CRYPTO_SMPS);
        Cipher_Cipher =findViewById(R.id.Cipher_Cipher);
        Steganography =findViewById(R.id.Steganography);
        DES_ALGO = findViewById(R.id.DES_ALGO);
        Cipher_Cipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent temp = new Intent(MainActivity.this,Cipher_Cipher.class);
                startActivity(temp);

            }
        });

        CRYPTO_SMPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent temp = new Intent(MainActivity.this,CRYPTO_SMPS.class);
                startActivity(temp);

            }
        });


        DES_ALGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(MainActivity.this,DES_ALGO.class);
                startActivity(temp);
            }
        });
        Steganography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(MainActivity.this,Stegnography.class);
                startActivity(temp);
            }
        });
        vf=findViewById(R.id.vf);
        int images [] ={R.drawable.cipher1,R.drawable.decryption,R.drawable.cipher3,R.drawable.stag2,R.drawable.steg};

        for (int i=0; i<images.length;i++)
        {
            flipper(images[i]);
        }
    }
    public void flipper(int images)
    {
        ImageView test =new ImageView(this);
        test.setBackgroundResource(images);
        vf.addView(test);
        vf.setFlipInterval(3000);
        vf.setAutoStart(true);
        vf.setInAnimation(this,android.R.anim.slide_in_left);

        vf.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}