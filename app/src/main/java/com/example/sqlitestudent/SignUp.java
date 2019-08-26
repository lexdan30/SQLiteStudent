package com.example.sqlitestudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class SignUp extends AppCompatActivity implements View.OnClickListener {


    TextView tvSighUpS;
    Button log;
    /**Button sbsignup;*/
    EditText uname,pword;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        setTitle("Login");

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        rFIDs();

        tvSighUpS.setOnClickListener(this);
        log.setOnClickListener(this);


    }
    public void rFIDs(){
        tvSighUpS = (TextView) findViewById(R.id.tvSignup1);
        log = (Button) findViewById(R.id.btnLogin);
        uname = (EditText) findViewById(R.id.etUsername);
        pword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        String userName= uname.getText().toString();
        String password= pword.getText().toString();
        switch (v.getId()) {

            case R.id.tvSignup1:

                Intent signup = new Intent(this, register.class);
                startActivity(signup);

                break;

            case R.id.btnLogin:
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
                // check if the Stored password matches with Password entered by user
                if(password.equals(storedPassword))
                {
                    //Intent x=new Intent(this, MainActivity.class);
                    //startActivity(x);
                    //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
                    //String username = userName;
                    prefs.edit().putString("username", userName).apply();

                    Intent login = new Intent(this, StudentMenu.class);
                    //login.putExtra("userName",username);

                    startActivity(login);
                    finish();

                    //Toast.makeText(SignUp.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                //dismiss();
                }
                else
                {
                    Toast.makeText(SignUp.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
