package com.example.myfirstapp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] mLocations;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    RadioButton tip15;
    RadioButton tip20;
    RadioButton tip25;
    EditText enter_bill;
    TextView tip_to_pay;
    TextView final_total;
    TextView total_bill_is;
    TextView email_addr;
    TextView trans_num;
    TextView trans_date;
    TextView Name;
    Double money;
    private Button mSendData;
    private Firebase ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab the saved information from the Sign In activity that was passed by Intent
        Intent myIntent = getIntent();

        String displayName = myIntent.getStringExtra("savedUser");
        String savedEmail = myIntent.getStringExtra("savedEmail");

        // Setting the Welcome <username> banner
        Name = (TextView) findViewById(R.id.user);
        Name.setText(displayName);

        // Declaring the objects on the Main page
        tip15 = (RadioButton) findViewById(R.id.tip15);
        tip20 = (RadioButton) findViewById(R.id.tip20);
        tip25 = (RadioButton) findViewById(R.id.tip25);
        enter_bill = (EditText) findViewById(R.id.enter_bill);
        total_bill_is = (TextView) findViewById(R.id.total_bill_is);
        final_total = (TextView) findViewById(R.id.total_to_pay_result);
        tip_to_pay = (TextView) findViewById(R.id.tip_to_pay);
        //email_addr = (TextView) findViewById(R.id.email_addr);
        //trans_num = (TextView) findViewById(R.id.trans_num);
        //trans_date = (TextView) findViewById(R.id.trans_date);

        // Getting ready to implement a Firebase database
        Firebase.setAndroidContext(this);

        mSendData = (Button) findViewById(R.id.calculate_total);
    }

    // Calculate button will perform the math for finding the Tip amount and the final bill amount
    // I have to round the amounts or they will end up being trailing, repeating numbers
    //
    @Override
    public void onClick(View v) {
        money = Double.parseDouble(enter_bill.getText().toString());

        // 15% Radio button is checked
        if (tip15.isChecked()) {
            double tip = (money * .15);
            double finalBill = tip + money;
            finalBill = Math.round(finalBill * 100.0) / 100.0;
            tip = Math.round(tip * 100.0) / 100.0;
            final_total.setText("$" + finalBill);
            tip_to_pay.setText("$" + tip);
        }

        // 20% Radio button is checked
        if (tip20.isChecked()) {
            double tip = (money * .2);
            double finalBill = tip + money;
            finalBill = Math.round(finalBill * 100.0) / 100.0;
            tip = Math.round(tip * 100.0) / 100.0;
            final_total.setText("$" + finalBill);
            tip_to_pay.setText("$" + tip);

        }

        // 25% Radio button is checked
        if (tip25.isChecked()) {
            double tip = (money * .25);
            double finalBill = tip + money;
            finalBill = Math.round(finalBill * 100.0) / 100.0;
            tip = Math.round(tip * 100.0) / 100.0;
            final_total.setText("$" + finalBill);
            tip_to_pay.setText("$" + tip);

        }
    }
}
