package com.example.sqlitestudent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;




public class Display extends AppCompatActivity{

    SQLiteDatabase dbase;
    DBHelper helper;
    TableLayout gridNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_items);
        RFIDs();
        helper = new DBHelper(getApplication(),"StudentFile",1);
        dbase = helper.getWritableDatabase();


      while(gridNumbers.getChildCount()>1)
        {
            gridNumbers.removeViewAt(1);
        }
        //display2.setOnClickListener(this);
        displayRecords();
    }
   public void RFIDs()
    {
        gridNumbers=(TableLayout)this.findViewById(R.id.grdNumbers);

    }

    public void displayRecords()
    {
        Cursor rsCursor;

        String [] rsFields= {"id","first","course","year"};

        rsCursor = dbase.query("StudentFile", rsFields, null, null, null, null, null, null);

        rsCursor.moveToFirst();
        if (!rsCursor.isAfterLast())
        {
            do {
                TableRow tRow = new TableRow(getApplication());

                TextView labelID=new TextView(getApplication());

                Integer iii=rsCursor.getInt(0);
                String o=iii.toString();
                labelID.setGravity(Gravity.CENTER);
                labelID.setText(o);
                labelID.setTextColor(Color.parseColor("#16ffeb"));
                tRow.addView(labelID);

       /*-----------------------------------------------------------------*/


                TextView labelDesc=new TextView(getApplication());

                labelDesc.setText(rsCursor.getString(1));
                labelDesc.setGravity(Gravity.CENTER);
                labelDesc.setTextColor(Color.parseColor("#16ffeb"));
                tRow.addView(labelDesc);


    /*-----------------------------------------------------------------*/

                TextView labelType=new TextView(getApplication());

                labelType.setText(rsCursor.getString(2));
                labelType.setGravity(Gravity.CENTER);
                labelType.setTextColor(Color.parseColor("#16ffeb"));
                tRow.addView(labelType);

        /*-----------------------------------------------------------------*/
                TextView labelPrice=new TextView(getApplication());

                labelPrice.setText(rsCursor.getString(3));
                labelPrice.setGravity(Gravity.CENTER);
                labelPrice.setTextColor(Color.parseColor("#16ffeb"));
                tRow.addView(labelPrice);
         /*-----------------------------------------------------------------*/
                gridNumbers.addView(tRow);
            } while(rsCursor.moveToNext());
        }
        rsCursor.close();

    }
}
