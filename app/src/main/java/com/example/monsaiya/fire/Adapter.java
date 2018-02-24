package com.example.monsaiya.fire;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Monsaiya on 21/2/2561.
 */

public class Adapter extends ArrayAdapter<item> {

    private Context mContext;
    private int mLayoutResId;
    private ArrayList<item> mIncomeList;


    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<item> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mLayoutResId = resource;
        this.mIncomeList = objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);
        NumberFormat nf = NumberFormat.getInstance();

        item item = mIncomeList.get(position);

        /*public final String title;
        public final String number;
        public final String time;
        public final String money;
        public final String sum;
        public final String date;*/

        TextView name = itemLayout.findViewById(R.id.t1);
        TextView number = itemLayout.findViewById(R.id.t2);
        TextView time = itemLayout.findViewById(R.id.t3);
        TextView money = itemLayout.findViewById(R.id.t4);
        TextView sum = itemLayout.findViewById(R.id.t5);
        TextView date = itemLayout.findViewById(R.id.t6);

        name.setText(item.title);
        number.setText(item.number);
        time.setText(item.time);
        money.setText(item.money);
        sum.setText(item.sum);
        date.setText(item.date);






        return itemLayout;
    }


}
