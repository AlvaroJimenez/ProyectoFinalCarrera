package com.example.alvar.tfg;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alvar on 18/01/2016.
 */
public class AdaptadorListView2 extends ArrayAdapter<Model> {

    private final Context context;
    private final ArrayList<Model> modelsArrayList;

    public AdaptadorListView2(Context context, ArrayList<Model> modelsArrayList) {

        super(context, R.layout.text_view, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.text_view, viewGroup, false);
        }
        TextView textView1 = (TextView) view.findViewById(R.id.textView_listview);
        //TextView textView2 = (TextView) view.findViewById(R.id.textView_listview2);
        final Model item = modelsArrayList.get(position);

        if(item.getName().contains("XM1000")) {
            //textview.setText(Html.fromHtml("<i><small><font color=\"#c5c5c5\">" + "Competitor ID: " + "</font></small></i>" + "<font color=\"#47a842\">" + compID + "</font>"));
            textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.temperature2, 0);
            textView1.setText("Temperature");
            //textView1.setText(Html.fromHtml(item.getName().substring(0, 6) + "<br>" + "<small><font color=\"#47a842\">" + item.getName().substring(7)+"</font></small>"));
        }
        else if(item.getName().contains("Power")) {

            textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.elec, 0);
            textView1.setText("Power");
            //textView1.setText(Html.fromHtml(item.getName().substring(0, 5) + "<br>" + "<small><font color=\"#47a842\">" + item.getName().substring(6)+"</font></small>"));

        }
        else if(item.getName().contains("Light")) {

            textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.light, 0);
            textView1.setText("Light");
           // textView1.setText(Html.fromHtml(item.getName().substring(0, 5) + "<br>"+ "<small><font color=\"#47a842\">" + item.getName().substring(6) + "</font></small>"));
           // textView2.setText(item.getName().substring(7));

        }
        else if(item.getName().contains("Presence")) {

            textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.presence_on, 0);
            textView1.setText("Presence");
           // textView1.setText(Html.fromHtml(item.getName().substring(0, 8) + "<br>"+ "<small><font color=\"#47a842\">" + item.getName().substring(9) + "</font></small>"));
            // textView2.setText(item.getName().substring(7));

        }

        else if(item.getName().contains("AirQuality")) {

            textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.airqualityicon, 0);
            textView1.setText("AirQuality");
          //  textView1.setText(Html.fromHtml(item.getName().substring(0, 10) + "<br>"+ "<small><font color=\"#47a842\">" + item.getName().substring(11) + "</font></small>"));
            // textView2.setText(item.getName().substring(7));

        }
        return view;

    }
}
