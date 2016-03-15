package com.example.alvar.tfg;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ControllerHvac extends Fragment {


    Context context;
    private SensorItem sensorItem;
    private String id;
    private SeekBar seekbar;
    private int progress = 0;
    private Boolean my;
    private TextView tempDigits;
    private ControllerHvac mainFragment;
    private ButtonRectangle buttonSend;
    private LinearLayout infoLayout;
    private ImageView imageView;
    private  TextView infodevice;
    private TextView tempSensorlabel;
    private TextView humSensorlabel;
    private boolean esActuador;
    private TextView nombre;
    private TextView ubicacion;
    private TextView statusHvac;
    private SwitchCompat switchView;

    public ControllerHvac(String id, Boolean my) {

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
        View view = inflater.inflate(R.layout.layout_controller_hvac,
                container, false);

        System.out.println(sensorItem.getId());


        tempSensorlabel = (TextView) view.findViewById(R.id.tempSensorLabel);
      //  humSensorlabel = (TextView) view.findViewById(R.id.humSensorLabel);
        FloatingActionButton delete = (FloatingActionButton)view.findViewById(R.id.deleteButton);
        FloatingActionButton add = (FloatingActionButton)view.findViewById(R.id.addButton);
        imageView = (ImageView) view.findViewById(R.id.DeviceIcon);
        infodevice = (TextView) view.findViewById(R.id.infodevice);
        tempDigits = (TextView) view.findViewById(R.id.tempDigits);
        LinearLayout infolayout = (LinearLayout) view.findViewById(R.id.infoLayout);
        switchView = (SwitchCompat) view.findViewById(R.id.Switch);
        statusHvac = (TextView) view.findViewById(R.id.on_off_info);
        String fontPath = "fonts/digital-7.ttf";
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(),
                fontPath);
        add.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);

        ubicacion = (TextView)view.findViewById(R.id.location);
        ubicacion.setText(sensorItem.getUbicacion());

        Double valuetemp = sensorItem.getTemperatura();
        tempSensorlabel.setText(String.format("%.2f ºC", valuetemp));
        tempSensorlabel.setTypeface(myTypeface);

     //   Double valuehum = sensorItem.getHumedad();
        //System.out.println("humedad " + valuehum);
        //humSensorlabel.setText(String.format("%.2f %%",valuehum));


        tempDigits.setTypeface(myTypeface);
        tempDigits.setText(String.format("%.2f ºC", valuetemp));

        buttonSend = (ButtonRectangle) view.findViewById(R.id.buttonSend);
     //   buttonSend.setText("ENVIAR");


        final SessionManager session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(SessionManager.KEY_NAME);

        seekbar = (SeekBar) view.findViewById(R.id.seekBar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue + 15;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something here,
                //if you want to do anything at the start of
                // touching the seekbar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Display the value in textview
                tempDigits.setText(progress + "ºC");
            }
        });


        if(!my && Cache.getInstance().myCjtSensores.exist(sensorItem.getId())) {
            delete.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            infolayout.setVisibility(View.VISIBLE);
            System.out.println("AAAAAAA");
        }
        else if(my) {
            delete.setVisibility(View.VISIBLE);
            infolayout.setVisibility(View.GONE);
            System.out.println("BBBBB");
            add.setVisibility(View.GONE);


        }
        else if(!my && !Cache.getInstance().myCjtSensores.exist(sensorItem.getId())) {
            add.setVisibility(View.VISIBLE);
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

        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int result = 0;
                String idActuator = Cache.getInstance().allCjtSensores.actuatorIdBySala(sensorItem.getUbicacion(),"HVAC");
                System.out.println("idActuador "+ idActuator);
                HttpRequestPut req = new HttpRequestPut(idActuator, Cache.getInstance().mainActivity, "Temperature",1, progress);
                try {
                    result = req.execute().get();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (ExecutionException e1) {
                    e1.printStackTrace();
                }

                if(result == 0)
                    Toast.makeText(getActivity(), "Connexion no disponible", Toast.LENGTH_SHORT).show();
            }
        });

        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int result = 0;
                String idActuator = Cache.getInstance().allCjtSensores.actuatorIdBySala(sensorItem.getUbicacion(), "HVAC");
                System.out.println("idActuador " + idActuator);
                HttpRequestPut req;

                if (switchView.isChecked()) {
                    statusHvac.setText("ON");
                    seekbar.setEnabled(true);
                    buttonSend.setEnabled(true);
                    statusHvac.setTextColor(Color.rgb(10, 187, 10));

                    req = new HttpRequestPut(idActuator, Cache.getInstance().mainActivity, "Temperature", 1, 0);

                } else {
                    statusHvac.setText("OFF");
                    seekbar.setEnabled(false);
                    buttonSend.setEnabled(false);
                    buttonSend.setEnabled(false);
                    statusHvac.setTextColor(Color.rgb(255, 0, 0));
                    req = new HttpRequestPut(idActuator, Cache.getInstance().mainActivity, "Temperature", 0, 0);

                }

                try {
                    result = req.execute().get();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (ExecutionException e1) {
                    e1.printStackTrace();
                }

                if(result == 0) {
                    Toast.makeText(getActivity(), "Connexion no disponible", Toast.LENGTH_SHORT).show();
                    switchView.setChecked(false);

                }


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
            imageView.setImageResource(R.drawable.door_open);
            infodevice.setText("OPEN");

        }
        else {
            imageView.setImageResource(R.drawable.door_close);
            infodevice.setText("CLOSE");
        }

    }

    public void onBackPressed() {
        getActivity().getFragmentManager().popBackStack();
    }

}
