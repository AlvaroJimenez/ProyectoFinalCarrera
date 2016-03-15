package com.example.alvar.tfg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alvar on 10/02/2016.
 */
public class Salac62 extends Fragment{

    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salac62(String p, String e) {
        this.planta = p;
        this.edificio = e;
        this.cjtSensores = Cache.getInstance().allCjtSensores;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salac62,
                container, false);


        return view;
    }









}
