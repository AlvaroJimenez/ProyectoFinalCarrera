package com.example.alvar.tfg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.Switch;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ControladorDetalleDispositivo extends Fragment {


    Context context;
    private SensorItem sensorItem;
    private String id;
    private Switch switchView;
    private SeekBar seekbar;
    private Boolean my;
    private TextView textView;
    private ControladorDetalleDispositivo mainFragment;
    private Button accept;
    private Button bttubicacion;
    private int progress = 0;
    private LinearLayout infoLayout;
    private ImageView imageView;
    private  TextView infopower;
    private boolean esActuador;
    public ControladorDetalleDispositivo() {

    }
    public ControladorDetalleDispositivo(String id, Boolean my) {

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
        View view = inflater.inflate(R.layout.fragment_fragment4,
                container, false);



        TextView nombre = (TextView) view.findViewById(R.id.dlbNombre);
        nombre.setText(sensorItem.getNombre());

        final TextView ubicacion = (TextView)view.findViewById(R.id.dlUbicacion);
        ubicacion.setText(sensorItem.getUbicacion());

        TextView lubicacion = (TextView)view.findViewById(R.id.lUbicacion);


        TextView temp = (TextView)view.findViewById(R.id.dlbTemperatura);
        Double value = sensorItem.getTemperatura();
        temp.setText(String.format("%.2f ºC", value));

        TextView hum = (TextView)view.findViewById(R.id.dlbAtributoB);
        hum.setText(sensorItem.getHumedad().toString());

        String fontPath = "fonts/digital-7.ttf";
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(),
                fontPath);

        temp.setTypeface(myTypeface);

        imageView = (ImageView) view.findViewById(R.id.imageView);
        final SwitchCompat switchView = (SwitchCompat) view.findViewById(R.id.Switch);


        TextView templabel = (TextView) view.findViewById(R.id.lbTemperatura);
        TextView humlabel = (TextView) view.findViewById(R.id.lbAtributoB);

        seekbar = (SeekBar) view.findViewById(R.id.seekBar);

        textView = (TextView)view.findViewById(R.id.textView1);

        textView.setText(seekbar.getProgress() + " ºC");

        accept = (Button)view.findViewById(R.id.buttonAcept);
        bttubicacion = (Button)view.findViewById(R.id.button_place);

        infopower = (TextView)view.findViewById(R.id.infopower);

        infoLayout = (LinearLayout)view.findViewById(R.id.infoLayout);
        infoLayout.setVisibility(View.GONE);

        if(sensorItem.getNombre().equals("XM1000")) {


            templabel.setText("Temperatura: ");
            humlabel.setText("Humedad: ");
            infopower.setVisibility(View.GONE);

            imageView.setVisibility(View.GONE);
            switchView.setVisibility(View.GONE);
            seekbar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);

        }
        else
        {
            infopower.setVisibility(View.VISIBLE);
            ubicacion.setVisibility(View.VISIBLE);
            temp.setVisibility(View.GONE);
            hum.setVisibility(View.GONE);
            lubicacion.setVisibility(View.VISIBLE);
            humlabel.setVisibility(View.GONE);
            templabel.setVisibility(View.GONE);
           // imageView.setImageResource(R.drawable.compon);
            imageView.setVisibility(View.VISIBLE);
            switchView.setVisibility(View.VISIBLE);
            seekbar.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            boolean on_off = false;
            on_off = (sensorItem.getValue() == 1);
            switchView.setChecked(on_off);
            cambiarIcono(sensorItem.getValue());

            accept.setVisibility(View.GONE);

        }

        //Control Listeners

        if(switchView.isChecked())
            cambiarIcono(1);
           // imageView.setImageResource(R.drawable.compon);
        else
            cambiarIcono(0);
         //   imageView.setImageResource(R.drawable.compoff);

        final SessionManager session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(SessionManager.KEY_NAME);



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
                textView.setText(progress + "ºC");
            }
        });

        FloatingActionButton delete = (FloatingActionButton)view.findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("¿Estas seguro de borrar la suscripcion a este sensor?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    ConexionBD deleteSensor = new ConexionBD(username,null,sensorItem.getId());
                                    deleteSensor.connectDelete();
                                    EliminarSensorDB con = new EliminarSensorDB(username, sensorItem.getId());
                                    con.connect();
                                } catch (Exception e) {}
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


        FloatingActionButton add = (FloatingActionButton)view.findViewById(R.id.addButton);



        add.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(getContext())
                        .setMessage("¿Desea suscribirse a este dispositivo?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    ConexionBD con = new ConexionBD(username, Cache.getInstance().mainActivity.getApplicationContext(), sensorItem.getId());
                                    con.connectAdd();
                                } catch (Exception e) {}
                            }
                            })
                             .setNegativeButton("No", null)
                             .show();
            }
        });

        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int result = 0;
                int input = 0;
                if (switchView.isChecked())
                    input = 1;
                else
                    input = 0;

                HttpRequestPut req = new HttpRequestPut(sensorItem.getId(), Cache.getInstance().mainActivity, sensorItem.getNombre(),1, input);
                try {
                    result = req.execute().get();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (ExecutionException e1) {
                    e1.printStackTrace();
                }

                try {switchView.setEnabled(false);Thread.sleep(2000);} catch (InterruptedException e) {}

                    if (input == 1 && result == 1)
                        cambiarIcono(1);
                       // imageView.setImageResource(R.drawable.compon);

                    else if (input == 0 && result == 1)
                        cambiarIcono(0);
                      //  imageView.setImageResource(R.drawable.compoff);
                    else Toast.makeText(getActivity(), "Connexion no disponible",Toast.LENGTH_SHORT).show();


                //  FragmentTransaction ft = getFragmentManager().beginTransaction();
               //  ft.detach(mainFragment).attach(mainFragment).commit();
            }
        });


        accept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int result = 0;
                HttpRequestPut req = new HttpRequestPut(sensorItem.getId(), Cache.getInstance().mainActivity, sensorItem.getNombre(),1, progress);
                try {
                    result = req.execute().get();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (ExecutionException e1) {
                    e1.printStackTrace();
                }


                try {Thread.sleep(1000);} catch (InterruptedException e) {}

                if(result == 0)
                    Toast.makeText(getActivity(), "Connexion no disponible",Toast.LENGTH_SHORT).show();

               // FragmentTransaction ft = getFragmentManager().beginTransaction();
              //  ft.detach(mainFragment).attach(mainFragment).commit();


            }
        });

        bttubicacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detalle sensor");
                Fragment fragment = null;

                if (ubicacion.getText().toString().contains("d60")) {

                    fragment = new Salad60("Planta0", "d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (ubicacion.getText().toString().contains("d61")) {

                    fragment = new Salad61("Planta1", "d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (ubicacion.getText().toString().contains("d62")) {

                    fragment = new Salad62("Planta2", "d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (ubicacion.getText().equals("d6S")) {

                    fragment = new Salad6s1("Planta S1", "d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }


            }
        });

        if(my) {
            add.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
            bttubicacion.setVisibility(View.VISIBLE);

        }
        else {
            bttubicacion.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            if (Cache.getInstance().myCjtSensores.exist(id))
            {
                infoLayout.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
            }

            else
                add.setVisibility(View.VISIBLE);

        }
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    private void cambiarIcono(int on)
    {
        if(on == 1 && sensorItem.getNombre().equals("Computer"))
            imageView.setImageResource(R.drawable.compon);
        else if(on == 1 && sensorItem.getNombre().equals("Light"))
            imageView.setImageResource(R.drawable.lighton);
        else if(on == 0 && sensorItem.getNombre().equals("Computer"))
            imageView.setImageResource(R.drawable.compoff);
        else if(on == 0 && sensorItem.getNombre().equals("Light"))
            imageView.setImageResource(R.drawable.lightoff);

        if(on == 1)
            infopower.setText("ON");
        else
            infopower.setText("OFF");

    }


    public void onBackPressed() {
        getActivity().getFragmentManager().popBackStack();
    }





}
