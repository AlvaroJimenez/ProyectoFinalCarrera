package com.example.alvar.tfg;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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




        TextView s1 = (TextView)view.findViewById(R.id.sS101);
        TextView s2 = (TextView) view.findViewById(R.id.sS102);
        TextView s3 = (TextView) view.findViewById(R.id.sS104A);
        TextView s4 = (TextView) view.findViewById(R.id.sS104B);
        TextView s5 = (TextView) view.findViewById(R.id.sS104);



        // s1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pcmap,R.drawable.pcmap,R.drawable.pcmap,R.drawable.pcmap);
        drawDevice("d6sS101", s1);
        drawDevice("d6sS102", s2);
        drawDevice("d6sS104A", s3);
        drawDevice("d6sS104B", s4);
        drawDevice("d6sS104", s5);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6sS101", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6sS102", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6sS104A", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6sS104B", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6sS104", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });



        return view;
    }

    private void drawDevice(String sala, TextView t)
    {

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        if(cjtSensores.existTypeSensorBySala(sala) && cjtSensores.existTypeActuatorBySala(sala))
            a = R.drawable.a;
        else if (cjtSensores.existTypeActuatorBySala(sala) && !cjtSensores.existTypeActuatorBySala(sala))
            b = R.drawable.s;
        else if(cjtSensores.existTypeSensorBySala(sala))
            b = R.drawable.s;



        t.setCompoundDrawablesWithIntrinsicBounds(a,b,c,d);
    }
}
