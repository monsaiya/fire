package com.example.monsaiya.fire;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class show extends AppCompatActivity {

    private DB mHelper;
    private SQLiteDatabase mDb;
    TextView tx_sum;
    NumberFormat nf;

    private ArrayList<item> mIncome = new ArrayList<>();
    private Adapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Button go = findViewById(R.id.go_back);
        mHelper = new DB(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();



        mAdapter = new Adapter(
                this,
                R.layout.item,
                mIncome
        );

        ListView lv = findViewById(R.id.list_item);
        lv.setAdapter(mAdapter);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(show.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadDataFromDb() {

        Cursor cursor = mDb.query(
                DB.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mIncome.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DB.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(DB.COL_TITLE));
            String number = cursor.getString(cursor.getColumnIndex(DB.COL_NUMBER));
            String time = cursor.getString(cursor.getColumnIndex(DB.COL_TIME));
            String money = cursor.getString(cursor.getColumnIndex(DB.COL_MONEY));
            String sum = cursor.getString(cursor.getColumnIndex(DB.COL_SUM));
            String date = cursor.getString(cursor.getColumnIndex(DB.COL_DATE));

            item item = new item(id, title, number,time, money,sum,date);
            mIncome.add(item);
        }
//        for (int i = 0 ;i < mIncome.size() ; i++){
//
//        }


    }
}
