package com.example.alvar.tfg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by alvar on 10/02/2016.
 */
public class Salac61 extends Fragment{

    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salac61(String p, String e) {
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
        View view = inflater.inflate(R.layout.salac61,
                container, false);

        TextView s101 = (TextView)view.findViewById(R.id.sS101);
        TextView s102 = (TextView) view.findViewById(R.id.sS102);
        TextView s103 = (TextView) view.findViewById(R.id.sS103);
        TextView s104 = (TextView) view.findViewById(R.id.s104);
        TextView s105 =  (TextView) view.findViewById(R.id.s105);
        TextView s106 = (TextView) view.findViewById(R.id.s106);
        TextView s107 = (TextView) view.findViewById(R.id.s107);
        TextView s108 = (TextView) view.findViewById(R.id.sS109);
        TextView s109 = (TextView) view.findViewById(R.id.sS109);
        TextView s110 = (TextView) view.findViewById(R.id.s110);
        TextView s111 = (TextView) view.findViewById(R.id.s111);
        TextView s112 = (TextView) view.findViewById(R.id.s112);
        TextView s113 = (TextView) view.findViewById(R.id.s113);
        TextView s114 = (TextView) view.findViewById(R.id.s114);
        TextView s115 = (TextView) view.findViewById(R.id.s115);
        TextView s117 = (TextView) view.findViewById(R.id.s117);
        TextView s118 = (TextView) view.findViewById(R.id.s118);
        TextView s119 = (TextView) view.findViewById(R.id.s119);
        TextView s120 = (TextView) view.findViewById(R.id.s120);



        drawDevice("c6s101", s101);
        drawDevice("c6s102", s102);
        drawDevice("c6s103", s103);
        drawDevice("c6s104", s104);
        drawDevice("c6s105", s105);
        drawDevice("c6s106", s106);



        s101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s101", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        s102.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s102", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        s103.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s103", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        s104.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s104", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        s105.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s105", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        s106.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s106", " ");
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


        t.setCompoundDrawablesWithIntrinsicBounds(a,b,c,d);
    }








}
