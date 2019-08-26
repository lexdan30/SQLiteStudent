package com.example.sqlitestudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity implements View.OnClickListener{

    Button bsignup;
    EditText uname,pword,pword2,fname, lname;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        RFIDs();

        bsignup.setOnClickListener(this);
    }
    public void RFIDs()
    {
        bsignup = (Button) findViewById(R.id.btnSigUp);
        fname = (EditText) findViewById(R.id.etFirstname);
        lname = (EditText) findViewById(R.id.etLastname);
        uname = (EditText) findViewById(R.id.etUsername);
        pword = (EditText) findViewById(R.id.etPassword);
        pword2 = (EditText) findViewById(R.id.etPassword2);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSigUp:


                Intent signup = new Intent(this, StudentMenu.class);
                startActivity(signup);

                String userName=uname.getText().toString();
                String firstName=fname.getText().toString();
                String lastName=lname.getText().toString();
                String password=pword.getText().toString();
                String confirmPassword=pword2.getText().toString();
                // check if any of the fields are vaccant
                if(userName.equals("")|| firstName.equals("") || lastName.equals("") || password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(register.this, "Field Vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(register.this, "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(register.this, "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}
