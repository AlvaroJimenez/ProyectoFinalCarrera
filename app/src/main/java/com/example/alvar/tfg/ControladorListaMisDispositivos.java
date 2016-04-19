package com.example.alvar.tfg;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
public class ControladorListaMisDispositivos extends Fragment {

    private Activity myAct;
    private MyCjtSensores mycjtSensores;
    private AllCjtSensores allCjtSensores;
    private String sala;
    private String[] values = null;
    private String[] valuesId = null;
    private String username;
    //private Drawable[] valuesImage = null;
    public ControladorListaMisDispositivos() {

    }

    public ControladorListaMisDispositivos(String sala, String username){
        /* Required empty public constructor */
        this.mycjtSensores = Cache.getInstance().myCjtSensores;
        this.allCjtSensores = Cache.getInstance().allCjtSensores;
        this.sala = sala;
        this.username = username;
        System.out.println(sala);


    }
    public void onActivityCreated(Bundle savedInstanceState)
    {

        final ListView listView = (ListView) getView().findViewById(R.id.listView);
        final TextView textView = (TextView) getView().findViewById(R.id.textSala);
        final ProgressBar spinner = (ProgressBar)getView().findViewById(R.id.progressBar1);


        if(!username.equals(" "))
        {
            values = mycjtSensores.getNombres();
            valuesId = mycjtSensores.getIds(sala, values.length);
            textView.setVisibility(View.GONE);
        }
        else {
            values = allCjtSensores.getNombresBySala(sala);
            valuesId = allCjtSensores.getIdBySala(sala, values.length);
            textView.setVisibility(View.VISIBLE);
            textView.setText(sala);
        }

        TextView info = (TextView) getView().findViewById(R.id.infoText);

        if(values.length == 0)
        {
            info.setVisibility(View.VISIBLE);
        }
        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this.getActivity(), R.layout.text_view2, values);
        listView.setAdapter(new AdaptadorListView(getView().getContext(), generateData()));
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //  Object o = lv.getItemAtPosition(position);

                String pos = String.valueOf(position);
                Log.d("myAct", pos);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detalle sensor");
                Fragment fragment = null;
                String id = valuesId[position];
                SensorItem s;


                if (Cache.getInstance().myCjtSensores.getNombreById(id).equals("Presence")) {
                    fragment = new ControllerPresence(id, true);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (Cache.getInstance().myCjtSensores.getNombreById(id).equals("XM1000")) {
                    fragment = new ControllerHvac(id, true);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (Cache.getInstance().myCjtSensores.getNombreById(id).equals("Power")) {
                    fragment = new ControllerPower(id, true);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (Cache.getInstance().myCjtSensores.getNombreById(id).equals("Light")) {
                    fragment = new ControllerLight(id, true);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (Cache.getInstance().myCjtSensores.getNombreById(id).equals("AirQuality")) {
                    fragment = new ControllerAirQuality(id, true);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    fragment = new ControladorDetalleDispositivo(id, true);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
            }

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub



            }
        });


        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Mis dispositivos");



        //  final ListView lv = (ListView)getView().findViewById(R.id.listView);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);


    }

    private ArrayList<Model> generateData(){

        ArrayList<Model> models = new ArrayList<Model>();

        for(int i =0; i < values.length;++i)
        {
            models.add(new Model(values[i]));
        }

        return models;
    }



}
