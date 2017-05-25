package com.example.klintank.thuligal;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by klintan.k on 25-05-2017.
 */

public class NewOrderActivity extends AppCompatActivity {

    EditText edtCustomerName, edtNumCans, edtMobile, edtAddress;
    TextView btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bg_dark_blue));
        }

        edtCustomerName = (EditText) findViewById(R.id.edt_customerName);
        edtNumCans = (EditText) findViewById(R.id.edt_canNo);
        edtMobile = (EditText) findViewById(R.id.edt_mobile);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        btnOrder = (TextView) findViewById(R.id.btn_order);

        btnOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                makeOrder();
            }
        });
    }

    private void makeOrder() {
        String customerName = edtCustomerName.getText().toString().trim();
        String canCount = edtNumCans.getText().toString().trim();
        String mobile = edtMobile.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if (customerName.equalsIgnoreCase("") && canCount.equalsIgnoreCase("")
                && mobile.equalsIgnoreCase("") && address.equalsIgnoreCase("")){
            showSnack("Enter the missing fields");
        }else if (customerName.equalsIgnoreCase("")){
            showSnack("Enter Customer name");
        }else if(canCount.equalsIgnoreCase("")){
            showSnack("Enter Number of Cans");
        }else if(mobile.equalsIgnoreCase("")){
            showSnack("Enter Contact number");
        }else if (mobile.length() != 10){
            showSnack("Enter Valid Contact number");
        }else if(address.equalsIgnoreCase("")){
            showSnack("Enter Address");
        }else {
            successDialog();
        }
    }

    private void successDialog() {
        //Create dialog
        final Dialog dialog = new Dialog(NewOrderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        LinearLayout ok = (LinearLayout) dialog.findViewById(R.id.btnOk);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //To empty the entered text
                edtCustomerName.setText("");
                edtNumCans.setText("");
                edtMobile.setText("");
                edtAddress.setText("");
                edtCustomerName.requestFocus();
                //To close the dialog after click ok button
                dialog.dismiss();
            }
        });
    }

    // Showing the status in Snackbar
    private void showSnack(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();
    }
}
