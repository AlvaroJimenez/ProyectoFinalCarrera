package com.example.alvar.tfg;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Salad60 extends Fragment {

    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salad60(String p, String e) {
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
        View view = inflater.inflate(R.layout.salad60,
                container, false);


        TextView s1 = (TextView)view.findViewById(R.id.s001);
        TextView s2 = (TextView) view.findViewById(R.id.s002);
        TextView s3 = (TextView) view.findViewById(R.id.s203);
        TextView s3bis = (TextView) view.findViewById(R.id.s003BIS);
        TextView s4 = (TextView) view.findViewById(R.id.s004);
        TextView s5 = (TextView) view.findViewById(R.id.s005);
        TextView s6 = (TextView) view.findViewById(R.id.s006);
        TextView s7 = (TextView) view.findViewById(R.id.s007);
        TextView s8 = (TextView) view.findViewById(R.id.s008);


       // s1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pcmap,R.drawable.pcmap,R.drawable.pcmap,R.drawable.pcmap);
        drawDevice("d6001", s1);
        drawDevice("d6002", s2);
        drawDevice("d6003", s3);
        drawDevice("d6004", s4);
        drawDevice("d6005", s5);
        drawDevice("d6006", s6);
        drawDevice("d6007", s7);
        drawDevice("d6008", s8);


        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6001"," ");
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
                fragment = new ControladorListaDispositivosSala("d6002"," ");
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

                fragment = new ControladorListaDispositivosSala("d6003"," ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();



            }
        });

        s3bis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("003BIS"," ");
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
                fragment = new ControladorListaDispositivosSala("d6004"," ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();



            }
        });

        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6005"," ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();



            }
        });

        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6006"," ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();



            }
        });

        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6007"," ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();



            }
        });

        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ControladorListaDispositivosSala("d6008", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
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
