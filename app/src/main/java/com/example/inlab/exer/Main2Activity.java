package com.example.inlab.exer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        TextView userTV = (TextView) findViewById(R.id.username);
        TextView hometwnTV = (TextView) findViewById(R.id.hometown);
        TextView birthplTV = (TextView) findViewById(R.id.birthplace);

        userTV.setText(extras.getString("username"));
        hometwnTV.setText(extras.getString("hometown"));
        birthplTV.setText(extras.getString("birthplace"));
    }
}
