package com.example.alvar.tfg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by alvar on 10/02/2016.
 */
public class Salac60 extends Fragment{

    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salac60(String p, String e) {
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
        View view = inflater.inflate(R.layout.salac60,
                container, false);


        TextView s101 = (TextView)view.findViewById(R.id.sS101);
        TextView s108 = (TextView) view.findViewById(R.id.sS109);
        TextView s102 = (TextView) view.findViewById(R.id.sS102);
        TextView s103 = (TextView) view.findViewById(R.id.sS103);
        TextView s104 =  (TextView) view.findViewById(R.id.s104);
        TextView s003 = (TextView) view.findViewById(R.id.s203);
        TextView s002 = (TextView) view.findViewById(R.id.s002);
        TextView s101A = (TextView) view.findViewById(R.id.s101A);
        TextView s101B = (TextView) view.findViewById(R.id.s101B);
        TextView s001 = (TextView) view.findViewById(R.id.s001);

        drawDevice("c6s101", s101);
        drawDevice("c6s108", s108);
        drawDevice("c6s102", s102);
        drawDevice("c6s103", s103);
        drawDevice("c6s104", s104);
        drawDevice("c6s3", s003);
        drawDevice("c6s2", s002);
        drawDevice("c6s101A", s101A);
        drawDevice("c6s101B", s101B);
        drawDevice("c6s001", s001);



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

        s108.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s108", " ");
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

        s003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s3", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        s002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s2", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        s101A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s101A", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        s101B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s101B", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        s001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("c6s001", " ");
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
