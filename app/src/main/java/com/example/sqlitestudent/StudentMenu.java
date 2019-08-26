package com.example.sqlitestudent;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class StudentMenu extends AppCompatActivity implements View.OnClickListener{
    Button insert,update,search,display,logout, delete;
    TextView uname;
    private String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_menu);
        //Toast.makeText(StudentMenu.this, "Hi! Welcome to my app. Page is under construction!", Toast.LENGTH_LONG).show();
        RFIDs();

        //Bundle extras = getIntent().getExtras();
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        //if (extras.containsKey("username")) {
        uname.setText("You are login as: " + username);
        //String username = extras.getString("username");
        Toast.makeText(StudentMenu.this, "Hi "+username+"! Welcome to my app. Page is under construction!", Toast.LENGTH_LONG).show();
        // put whatever code you want here to show the username

        //}
        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        search.setOnClickListener(this);
        display.setOnClickListener(this);
        logout.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    public void RFIDs()
    {
        uname = (TextView) findViewById(R.id.tvLogonAs);
        insert = (Button) findViewById(R.id.btnInsert);
        update = (Button) findViewById(R.id.btnUpdate);
        search = (Button) findViewById(R.id.btnSearch);
        display = (Button) findViewById(R.id.btnDisplay);
        logout = (Button) findViewById(R.id.btnLogout);
        delete = (Button) findViewById(R.id.btnDelete);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                Intent i=new Intent(this, InsertStudent.class);
                startActivity(i);
                break;
            case R.id.btnUpdate:
                Intent u=new Intent(this, InsertStudent.class);
                startActivity(u);
                Toast.makeText(StudentMenu.this, "Update not yet available!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSearch:
                Intent s=new Intent(this, Search.class);
                startActivity(s);
                Toast.makeText(StudentMenu.this, "Search available!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnDisplay:
                Intent d=new Intent(this, Display.class);
                startActivity(d);
                Toast.makeText(StudentMenu.this, "Display is available!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnLogout:
                Intent e=new Intent(this, SignUp.class);
                startActivity(e);
                Toast.makeText(StudentMenu.this, "Successfully Logout !", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.btnDelete:
                Intent f=new Intent(this, Delete.class);
                startActivity(f);
                Toast.makeText(StudentMenu.this, "Delete Available !", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
