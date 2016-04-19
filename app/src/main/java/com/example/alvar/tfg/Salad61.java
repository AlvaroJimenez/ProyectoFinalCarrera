package com.example.alvar.tfg;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by alvar on 17/01/2016.
 */
public class Salad61 extends Fragment {


    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salad61(String p, String e) {
        this.planta = p;
        this.edificio = e;
        this.cjtSensores = Cache.getInstance().allCjtSensores;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salad61,
                container, false);

        TextView s1 = (TextView)view.findViewById(R.id.sS101);
        TextView s2 = (TextView) view.findViewById(R.id.sS102);
        TextView s3 = (TextView) view.findViewById(R.id.sS103);
        TextView s4 = (TextView) view.findViewById(R.id.s104);
        TextView s5 = (TextView) view.findViewById(R.id.s105);
        TextView s6 = (TextView) view.findViewById(R.id.s106);
        TextView s7 = (TextView) view.findViewById(R.id.s107);
        TextView s8 = (TextView) view.findViewById(R.id.sS109);
        TextView s9 = (TextView) view.findViewById(R.id.sS109);
        TextView s10 = (TextView) view.findViewById(R.id.s110);
        TextView s11 = (TextView) view.findViewById(R.id.s111);
        TextView s12 = (TextView) view.findViewById(R.id.s112);
        TextView s13 = (TextView) view.findViewById(R.id.s113);
        TextView s14 = (TextView) view.findViewById(R.id.s114);
        TextView s15 = (TextView) view.findViewById(R.id.s115);

        drawDevice("d6101", s1);
        drawDevice("d6102", s2);
        drawDevice("d6103", s3);
        drawDevice("d6104", s4);
        drawDevice("d6105", s5);
        drawDevice("d6106", s6);
        drawDevice("d6107", s7);
        drawDevice("d6109", s8);
        drawDevice("d6110", s9);
        drawDevice("d6111", s10);
        drawDevice("d6112", s11);
        drawDevice("d6113", s12);
        drawDevice("d6114", s13);
        drawDevice("d6115", s15);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6101", " ");
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

                fragment = new ControladorListaDispositivosSala("d6102", " ");
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

                fragment = new ControladorListaDispositivosSala("d6103", " ");
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

                fragment = new ControladorListaDispositivosSala("d6104", " ");
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

                fragment = new ControladorListaDispositivosSala("d6105", " ");
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

                fragment = new ControladorListaDispositivosSala("d6106", " ");
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

                fragment = new ControladorListaDispositivosSala("d6107", " ");
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

                fragment = new ControladorListaDispositivosSala("d6108", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6109", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6110", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6111", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6112", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6113", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6114", " ");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

        s15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;

                fragment = new ControladorListaDispositivosSala("d6115", " ");
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

        if(cjtSensores.existTypeSensorBySala(sala) && cjtSensores.existTypeActuatorBySala(sala)) {
            a = R.drawable.a;

        }
        else if (cjtSensores.existTypeActuatorBySala(sala) && !cjtSensores.existTypeActuatorBySala(sala)) {
            b = R.drawable.s;
            System.out.println("Encontrado");

        }
        else if(cjtSensores.existTypeSensorBySala(sala))
            b = R.drawable.s;



        t.setCompoundDrawablesWithIntrinsicBounds(a,b,c,d);
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

}




