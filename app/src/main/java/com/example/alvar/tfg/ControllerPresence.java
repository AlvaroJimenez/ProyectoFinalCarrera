package com.example.alvar.tfg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class ControllerPresence extends Fragment {


    Context context;
    private SensorItem sensorItem;
    private String id;
    private Boolean my;
    private TextView textView;
    private ControllerPresence mainFragment;
    private Button bttubicacion;
    private LinearLayout infoLayout;
    private ImageView imageView;
    private  TextView infodevice;
    private boolean esActuador;
    private TextView nombre;
    private TextView ubicacion;
    public ControllerPresence() {

    }
    public ControllerPresence(String id, Boolean my) {

        this.id = id;
        this.my = my;
        this.mainFragment = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        if(my)
            sensorItem = Cache.getInstance().myCjtSensores.getSensorItemById(id);
        else {
            sensorItem = Cache.getInstance().allCjtSensores.getSensorItemById(id);
            System.out.println("ENCONTRADO");

        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_controller_presence,
                container, false);

        System.out.println(sensorItem.getId());


        FloatingActionButton delete = (FloatingActionButton)view.findViewById(R.id.deleteButton);
        FloatingActionButton add = (FloatingActionButton)view.findViewById(R.id.addButton);
        LinearLayout infolayout = (LinearLayout) view.findViewById(R.id.infoLayout);

        ubicacion = (TextView)view.findViewById(R.id.location);
        ubicacion.setText(sensorItem.getUbicacion());



        imageView = (ImageView) view.findViewById(R.id.DeviceIcon);

        infodevice = (TextView) view.findViewById(R.id.infodevice);

        int value = sensorItem.getValue();

        cambiarIcono(value);


        final SessionManager session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(SessionManager.KEY_NAME);


        if(!my && Cache.getInstance().myCjtSensores.exist(sensorItem.getId())) {
            delete.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            infolayout.setVisibility(View.VISIBLE);
            System.out.println("AAAAAAA");
        }
        else if(my) {
            delete.setVisibility(View.VISIBLE);
            System.out.println("BBBBB");
            infolayout.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        }
        else if(!my && !Cache.getInstance().myCjtSensores.exist(sensorItem.getId())) {
            add.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
            infolayout.setVisibility(View.GONE);
            System.out.println("CCCCCC");
        }

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("¿Estas seguro de borrar la suscripcion a este sensor?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    ConexionBD deleteSensor = new ConexionBD(username, null, sensorItem.getId());
                                    deleteSensor.connectDelete();
                                    EliminarSensorDB con = new EliminarSensorDB(username, sensorItem.getId());
                                    con.connect();
                                } catch (Exception e) {
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("¿Desea suscribirse a este dispositivo?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    ConexionBD con = new ConexionBD(username, Cache.getInstance().mainActivity.getApplicationContext(), sensorItem.getId());
                                    con.connectAdd();
                                } catch (Exception e) {
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    private void cambiarIcono(int on)
    {
        if(on == 1) {
            imageView.setImageResource(R.drawable.presence_on);


        }
        else {
            imageView.setImageResource(R.drawable.presence_off);

        }

    }

    public void onBackPressed() {
        getActivity().getFragmentManager().popBackStack();
    }





}
