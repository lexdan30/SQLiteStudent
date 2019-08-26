package com.example.sqlitestudent;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class InsertStudent extends AppCompatActivity implements View.OnClickListener {
    Button insertItem;
    EditText id,last,first,middle,year,course;

    SQLiteDatabase dbase;

    TableLayout gridNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_item);
       RFIDs();

        DBHelper helper = new DBHelper(getApplication(),"StudentFile",1);
        dbase = helper.getWritableDatabase();
        //Toast.makeText(InsertBevItem.this, "Hi! Welcome to my app. APP IS NOT YET AVAILABLE!", Toast.LENGTH_LONG).show();




       insertItem.setOnClickListener(this);
        // display.setOnClickListener(this);
    }
    public void RFIDs()
    {
        insertItem=(Button)findViewById(R.id.btnInsertNew);
        id =(EditText)findViewById(R.id.etidNo);
        last =(EditText)findViewById(R.id.etlName);
        first =(EditText)findViewById(R.id.etfName);
        middle=(EditText)findViewById(R.id.etmName);
        course=(EditText)findViewById(R.id.etCourse);
        year =(EditText)findViewById(R.id.etYear);

    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnInsertNew:

                String ids = id.getText().toString();
                int num = Integer.parseInt(ids);
                String firsts = first.getText().toString();
                String lasts = last.getText().toString();
                String middles = middle.getText().toString();
                String courses = course.getText().toString();
                String years = year.getText().toString();


                ContentValues rsValues = new ContentValues();
                Cursor rsCursor;
                long x;

                String [] rsFields={"id","first","last","middle","course","year"};
                rsCursor=dbase.query("StudentFile",rsFields,"id = " + num,null,null,null,null,null);
                rsCursor.moveToFirst();
                if(rsCursor.isAfterLast())
                {
                    rsValues.put("id", num);
                    rsValues.put("first", firsts);
                    rsValues.put("last", lasts);
                    rsValues.put("middle", middles);
                    rsValues.put("course", courses);
                    rsValues.put("year", years);

                    x = dbase.insert("StudentFile", null, rsValues);

                    if (x != -1) {
                        // rsCursor.close();
                        Toast.makeText(InsertStudent.this, "Student Saved!", Toast.LENGTH_LONG).show();
                        rsCursor.close();
                    }else {
                        Toast.makeText(InsertStudent.this, "Error!", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(InsertStudent.this, "Student already Exist!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
