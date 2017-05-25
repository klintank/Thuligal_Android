package com.example.klintank.thuligal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by klintan.k on 23-05-2017.
 */

public class RegisterActivity extends AppCompatActivity{

    EditText edtStoreName, edtOwnerName, edtMobile, edtAddress;
    TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bg_dark_blue));
        }

        edtStoreName = (EditText) findViewById(R.id.edt_storeName);
        edtOwnerName = (EditText) findViewById(R.id.edt_ownerName);
        edtMobile = (EditText) findViewById(R.id.edt_mobile);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        btnRegister = (TextView) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register() {
        String storeName = edtStoreName.getText().toString().trim();
        String ownerName = edtOwnerName.getText().toString().trim();
        String mobile = edtMobile.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if (storeName.equalsIgnoreCase("") && ownerName.equalsIgnoreCase("")
                && mobile.equalsIgnoreCase("") && address.equalsIgnoreCase("")){
            showSnack("Enter the missing fields");
        }else if (storeName.equalsIgnoreCase("")){
            showSnack("Enter Store name");
        }else if(ownerName.equalsIgnoreCase("")){
            showSnack("Enter Owner name");
        }else if(mobile.equalsIgnoreCase("")){
            showSnack("Enter Contact number");
        }else if (mobile.length() != 10){
            showSnack("Enter Valid Contact number");
        }else if(address.equalsIgnoreCase("")){
            showSnack("Enter address");
        }else {
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    // Showing the status in Snackbar
    private void showSnack(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();
    }
}
