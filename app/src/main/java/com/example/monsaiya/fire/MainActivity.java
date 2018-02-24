package com.example.monsaiya.fire;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String s_unit ;
    String s_time_unit ;
    double sum ;
    double sum2 ;
    EditText name;
    EditText ele ;
    EditText time;
    EditText bath ;
    EditText time_min;
    Spinner unit;

    Button b  ;
    Button go ;
    TextView text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name123);
        ele = findViewById(R.id.eletric);
        time = findViewById(R.id.time);
        bath = findViewById(R.id.bath);
        unit = findViewById(R.id.unit);
        time_min = findViewById(R.id.time_min);

         b = findViewById(R.id.button);
         go = findViewById(R.id.go);
         text = findViewById(R.id.text);

        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_unit = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!name.getText().toString().isEmpty() && !ele.getText().toString().isEmpty() && !time.getText().toString().isEmpty()&& !time_min.getText().toString().isEmpty() && !bath.getText().toString().isEmpty() && Double.parseDouble(ele.getText().toString() ) > 0 && Double.parseDouble(time.getText().toString()) > -1 && Double.parseDouble(bath.getText().toString()) > 0 && Double.parseDouble(time_min.getText().toString()) > -1  ) {
                        if (s_unit.equals("W") ){
                            sum = (Double.parseDouble(ele.getText().toString()) * (Double.parseDouble(time.getText().toString()) + (Double.parseDouble(time_min.getText().toString()) / 60))) / 1000;
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            BigDecimal bd = new BigDecimal(sum2);
                            String sum1 = String.valueOf(bd.setScale(2, RoundingMode.UP));
                            text.setText(sum1);
                            save();


                        } else if (s_unit.equals("A") ) {
                            sum = ((Double.parseDouble(ele.getText().toString()) * 220) * (Double.parseDouble(time.getText().toString()) + (Double.parseDouble(time_min.getText().toString()) / 60))) / 1000;
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            BigDecimal bd = new BigDecimal(sum2);
                            String sum1 = String.valueOf(bd.setScale(2, RoundingMode.UP));
                            text.setText(sum1);
                            save();


                        } else if (s_unit.equals("KW") ) {
                            sum = (Double.parseDouble(ele.getText().toString()) * (Double.parseDouble(time.getText().toString()) + (Double.parseDouble(time_min.getText().toString()) / 60)));
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            BigDecimal bd = new BigDecimal(sum2);
                            String sum1 = String.valueOf(bd.setScale(2, RoundingMode.UP));
                            text.setText(sum1);
                            save();


                        } else if (s_unit.equals("MA") ) {
                            sum = (((Double.parseDouble(ele.getText().toString()) * 0.001) * 220) * (Double.parseDouble(time.getText().toString()) + (Double.parseDouble(time_min.getText().toString()) / 60))) / 1000;
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            BigDecimal bd = new BigDecimal(sum2);
                            String sum1 = String.valueOf(bd.setScale(2, RoundingMode.UP));
                            text.setText(sum1);
                            save();


                        } /*else if (s_unit.equals("W") && s_time_unit.equals("MINUTE")) {
                            sum = (Double.parseDouble(ele.getText().toString()) * (Double.parseDouble(time.getText().toString()) / 60)) / 1000;
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            text.setText(String.valueOf(sum2));
                            save();


                        } else if (s_unit.equals("A") && s_time_unit.equals("MINUTE")) {
                            sum = ((Double.parseDouble(ele.getText().toString()) * 220) * (Double.parseDouble(time.getText().toString()) / 60)) / 1000;
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            text.setText(String.valueOf(sum2));
                            save();


                        } else if (s_unit.equals("KW") && s_time_unit.equals("MINUTE")) {
                            sum = (Double.parseDouble(ele.getText().toString()) * (Double.parseDouble(time.getText().toString()) / 60));
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            text.setText(String.valueOf(sum2));
                            save();


                        } else if (s_unit.equals("MA") && s_time_unit.equals("MINUTE")) {
                            sum = (((Double.parseDouble(ele.getText().toString()) * 0.001) * 220) * (Double.parseDouble(time.getText().toString()) / 60)) / 1000;
                            sum2 = sum * Double.parseDouble(bath.getText().toString());
                            text.setText(String.valueOf(sum2));
                            save();


                        }*/
                    }
                }

            });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,show.class);
                startActivity(intent);

            }
        });



    }

    public void save(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        String name1 = name.getText().toString();
        String number1 = ele.getText().toString() + "   " + s_unit;
        String time1 = time.getText().toString() +" : "+time_min.getText().toString()+ "   Hour"  ;
        String money1 = bath.getText().toString() + "   บาท";
        BigDecimal bd = new BigDecimal(sum2);
        String sum1 = String.valueOf(bd.setScale(2, RoundingMode.UP)) + "   บาท";
        String date  = formattedDate;

        ContentValues cv = new ContentValues();
        cv.put(DB.COL_TITLE, name1);
        cv.put(DB.COL_NUMBER, number1);
        cv.put(DB.COL_TIME, time1);
        cv.put(DB.COL_MONEY, money1);
        cv.put(DB.COL_SUM, sum1);
        cv.put(DB.COL_DATE, date);

        DB dbHelper = new DB(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert(DB.TABLE_NAME, null, cv);

    }




}
