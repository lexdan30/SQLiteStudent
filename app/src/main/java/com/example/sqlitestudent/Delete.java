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


public class Delete extends AppCompatActivity implements View.OnClickListener {

    Button bDelete;
    EditText num1, desc1, type1, price1;

    SQLiteDatabase dbase;

    TableLayout gridNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        RFIDs();

        DBHelper helper = new DBHelper(getApplication(), "StudentFile", 1);
        dbase = helper.getWritableDatabase();

        bDelete.setOnClickListener(this);

    }

    public void RFIDs() {
        bDelete = (Button) findViewById(R.id.btnDelete);
        num1 = (EditText) findViewById(R.id.etidNo);
        gridNumbers=(TableLayout)this.findViewById(R.id.grdNumbers);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDelete:

                String value = num1.getText().toString();
                int num = Integer.parseInt(value);

                Cursor rsCursor;
                long x;

                String[] rsFields = {"id","first","course","year"};
                rsCursor = dbase.query("StudentFile", rsFields, "id = " + num, null, null, null, null, null);
                rsCursor.moveToFirst();
                if (!rsCursor.isAfterLast()) {
                    Integer o = rsCursor.getInt(0);
                    String i = rsCursor.getString(1);
                    //desc1.setText(i);
                    String ii = rsCursor.getString(2);
                    //type1.setText(ii);
                    String iii = rsCursor.getString(3);
                    //price1.setText(o);
                    TableRow tRow = new TableRow(getApplication());

                    TextView labelID=new TextView(getApplication());


                    String oo=o.toString();
                    labelID.setGravity(Gravity.CENTER);
                    labelID.setText(oo);
                    labelID.setTextColor(Color.parseColor("#16ffeb"));
                    tRow.addView(labelID);

       /*-----------------------------------------------------------------*/


                    TextView labelDesc=new TextView(getApplication());

                    labelDesc.setText(i);
                    labelDesc.setGravity(Gravity.CENTER);
                    labelDesc.setTextColor(Color.parseColor("#16ffeb"));
                    tRow.addView(labelDesc);


    /*-----------------------------------------------------------------*/

                    TextView labelType=new TextView(getApplication());

                    labelType.setText(ii);
                    labelType.setGravity(Gravity.CENTER);
                    labelType.setTextColor(Color.parseColor("#16ffeb"));
                    tRow.addView(labelType);

        /*-----------------------------------------------------------------*/
                    TextView labelPrice=new TextView(getApplication());

                    labelPrice.setText(iii);
                    labelPrice.setGravity(Gravity.CENTER);
                    labelPrice.setTextColor(Color.parseColor("#16ffeb"));
                    tRow.addView(labelPrice);
         /*-----------------------------------------------------------------*/
                    gridNumbers.addView(tRow);
                    dbase.delete("StudentFile", "id = " + num, null);
                    rsCursor.close();
                } else {


                    Toast.makeText(Delete.this, "Not in the List!", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}