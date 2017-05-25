package com.example.klintank.thuligal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by klintan.k on 23-05-2017.
 */

public class MainActivity extends AppCompatActivity {

    ImageView addDialog;
    TextView totalCan;
    LinearLayout newOrder, cancelOrder, orderHistory;
    int canCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bg_dark_blue));
        }

        totalCan = (TextView) findViewById(R.id.total_can_count);
        addDialog = (ImageView) findViewById(R.id.btn_add_can);
        newOrder = (LinearLayout) findViewById(R.id.btn_new_order);
        cancelOrder = (LinearLayout) findViewById(R.id.btn_cancel_order);
        orderHistory = (LinearLayout) findViewById(R.id.btn_history);

        newOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewOrderActivity.class);
                startActivity(intent);
            }
        });

        addDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    public void showAddDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_can);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        LinearLayout add = (LinearLayout) dialog.findViewById(R.id.btn_add_count);
        LinearLayout minus = (LinearLayout) dialog.findViewById(R.id.btn_minus_count);
        ImageView close = (ImageView) dialog.findViewById(R.id.btn_close_dialog);
        final TextView count = (TextView) dialog.findViewById(R.id.txt_can_count);
        TextView submit = (TextView) dialog.findViewById(R.id.btn_submit);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                canCount = Integer.valueOf(count.getText().toString());
                if (canCount >= 0){
                    canCount = canCount + 1;
                    count.setText(String.valueOf(canCount));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                canCount = Integer.valueOf(count.getText().toString());
                if (canCount > 0){
                    canCount = canCount - 1;
                    count.setText(String.valueOf(canCount));
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String can = count.getText().toString();
                totalCan.setText(can);
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
