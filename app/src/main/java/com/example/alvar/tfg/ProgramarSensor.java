package com.example.alvar.tfg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProgramarSensor extends Fragment {


    public ProgramarSensor() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_programar_sensor,
                container, false);


        return view;
    }


    public void onActivityCreated(Bundle savedInstanceState)
    {





        super.onActivityCreated(savedInstanceState);
    }

}
