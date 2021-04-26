package com.example.crypto_smps_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class DES_ALGO extends AppCompatActivity {
    EditText keytext, normaltext, ciphertext;
    Button copy_normal, copy_cipher, encrypt, decrypt, delete_normal, delete_cipher;
    TextView char_count, char_count2;
    Context c;
    private javaApp a;
    private String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_e_s__a_l_g_o);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#2a9db7"));
        }
        c = DES_ALGO.this;

        normaltext = findViewById(R.id.normal_text);
        keytext = findViewById(R.id.key);
        ciphertext = findViewById(R.id.ciphertext);
        copy_cipher = findViewById(R.id.copy2_normal);
        copy_normal = findViewById(R.id.copy1_normal);
        encrypt = findViewById(R.id.encrypt);
        decrypt = findViewById(R.id.decrypt);
        delete_normal = findViewById(R.id.delete1_normal);
        delete_cipher = findViewById(R.id.delete2_cipher);
        char_count = findViewById(R.id.CharCount);
        char_count2 = findViewById(R.id.CharCount2);

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (normaltext.getText().toString().matches("") || keytext.getText().toString().matches("")) {
                    a.ToastMaker(c, "Enter the encryted text and key");
                } else if (keytext.getText().toString().length() != 8) {
                    a.ToastMaker(c, "Enter the key of 8 characters");
                } else {
                    ciphertext.setText(encrypt(normaltext.getText().toString(), c));
                }

            }
        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ciphertext.getText().toString().matches("") || keytext.getText().toString().matches("")) {
                    a.ToastMaker(c, "Enter the encryted text and key");
                } else if (keytext.getText().toString().length() != 8) {
                    a.ToastMaker(c, "Enter the key of 8 characters");
                } else {
                    normaltext.setText(decrypt(ciphertext.getText().toString(), c));
                }
            }
        });
        copy_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("cipher text", normaltext.getText().toString());
                clipboard.setPrimaryClip(clip);
                a.ToastMaker(c, "Input message Copied");
            }
        });
        copy_cipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("cipher text", ciphertext.getText().toString());
                clipboard.setPrimaryClip(clip);
                a.ToastMaker(c, " Encrypted message Copied");
            }
        });
        delete_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normaltext.setText("");
            }
        });
        delete_cipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciphertext.setText("");
            }
        });

        normaltext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                char_count.setText(normaltext.getText().toString().length() + "");
            }
        });

        ciphertext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                char_count2.setText(ciphertext.getText().toString().length() + "");
            }


        });
    }

    public String decrypt(String value, Context c) {
        String coded;
        String result;
        if (value.startsWith("code==")){
            coded=value.substring(6,value.length()).trim();
        }
        else
        {
            coded=value.trim();
        }
        try {
            byte[] bytesDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
            SecretKeySpec key = new SecretKeySpec(keytext.getText().toString().getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/ZEROBytePadding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] textDecrypt = cipher.doFinal(bytesDecoded);
            result = new String(textDecrypt);
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        catch (Exception e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        return result;
    }
    public String encrypt(String value, Context c) {
        String crypted = "";
        try {
            byte[] cleartext = value.getBytes("UTF-8");
            SecretKeySpec key = new SecretKeySpec(keytext.getText().toString().getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/ZeroBytePadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            crypted = Base64.encodeToString(cipher.doFinal(cleartext),Base64.DEFAULT);
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        catch (Exception e) {
            e.printStackTrace();
            a.DialogMaker(c, "Encrypt Error", "Error" + "\n" + e.getMessage());
            return "Encrypt Error";
        }
        return crypted;
    }
}
