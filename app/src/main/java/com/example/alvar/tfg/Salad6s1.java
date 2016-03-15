package com.example.alvar.tfg;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alvar on 07/02/2016.
 */
public class Salad6s1 extends Fragment {

    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salad6s1(String p, String e) {
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
        View view = inflater.inflate(R.layout.salad6s1,
                container, false);


        return view;
    }
}
