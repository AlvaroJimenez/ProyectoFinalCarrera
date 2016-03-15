package com.example.alvar.tfg;

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

import java.util.HashMap;

public class ControladorPuerta extends Fragment {


    Context context;
    private SensorItem sensorItem;
    private String id;
    private SwitchCompat switchView;
    private Boolean my;
    private TextView textView;
    private ControladorPuerta mainFragment;
    private Button bttubicacion;
    private LinearLayout infoLayout;
    private ImageView imageView;
    private  TextView infodevice;
    private boolean esActuador;
    private TextView nombre;
    private TextView ubicacion;
    public ControladorPuerta() {

    }
    public ControladorPuerta(String id, Boolean my) {

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
        View view = inflater.inflate(R.layout.layout_controller_door,
                container, false);


        System.out.println(sensorItem.getId());

        ubicacion = (TextView)view.findViewById(R.id.location);
        ubicacion.setText(sensorItem.getUbicacion());

        switchView = (SwitchCompat) view.findViewById(R.id.Switch);

        imageView = (ImageView) view.findViewById(R.id.DeviceIcon);

        infodevice = (TextView) view.findViewById(R.id.infodevice);


        final SessionManager session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(SessionManager.KEY_NAME);


        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (switchView.isChecked())
                    cambiarIcono(1);
                else
                    cambiarIcono(0);


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
