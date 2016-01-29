package com.example.inlab.exer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.signInBtn);
        btn.setOnClickListener(this);
        User.dbhelp = new UsersHelper(getApplicationContext());

        createUser();

    }

    private void createUser() {
        if (User.read("VMG") == null){
            User user = new User("VMG", "BCN", "BCN");
            user.setPassword("123456");

            user.save();
            Log.i("MyAPP", "User created");
        }
    }

    @Override
    public void onClick(View v) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        User user = User.read(username.getText().toString());
        if (user != null && user.comparePassword(password.getText().toString())) {
            Log.i("MyAPP", "Login successful");
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            Bundle extras = new Bundle();
            extras.putString("username", user.getUserName());
            extras.putString("hometown", user.getHometown());
            extras.putString("birthplace", user.getBirthplace());
            intent.putExtras(extras);
            startActivity(intent);
        } else {
            Log.i("MyAPP", "Login failed");
        }
    }
}
