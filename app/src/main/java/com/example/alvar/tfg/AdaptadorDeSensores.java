package com.example.alvar.tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by alvar on 01/10/2015.
 */
public class AdaptadorDeSensores extends BaseAdapter {

    private Context context;

    public AdaptadorDeSensores(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Sensor.ITEMS.length;
    }

    @Override
    public Sensor getItem(int position) {
        return Sensor.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenSensor = (ImageView) view.findViewById(R.id.imagenSensor);
        TextView nombre = (TextView) view.findViewById(R.id.nombre_coche);


        final Sensor item = getItem(position);
        Glide.with(imagenSensor.getContext())
                .load(item.getIdDrawable())
                .into(imagenSensor);

        if (item.getNombre().equals("Temperatura"))
        {
            nombre.setTextSize(13);
        }

        nombre.setText(item.getNombre());

        return view;
    }




}
