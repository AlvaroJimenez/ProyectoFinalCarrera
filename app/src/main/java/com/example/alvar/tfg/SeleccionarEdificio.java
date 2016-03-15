package com.example.alvar.tfg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class SeleccionarEdificio extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private AllCjtSensores cjtSensores;
    public SeleccionarEdificio(AllCjtSensores cjtSensores) {
        this.cjtSensores = cjtSensores;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seleccionar_edificio, container, false);


        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
       final ImageView d6 = (ImageView) getView().findViewById(R.id.d6);
        final ImageView c6 = (ImageView) getView().findViewById(R.id.c6);
        final  String[] plantas = null;
        final TextView textView1 = (TextView)getView().findViewById(R.id.textView1);
        final TextView textView2 = (TextView)getView().findViewById(R.id.textView2);
        final ListView listView1 = (ListView)getView().findViewById(R.id.listView2);
        final ListView listView2 = (ListView)getView().findViewById(R.id.listView3);


        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listView1.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.GONE);
                textView1.setVisibility(View.VISIBLE);
                d6.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                c6.setVisibility(View.VISIBLE);
                final  String[] plantas  = new String[]{"Planta S1","Planta 0","Planta 1","Planta 2"};
               // ArrayAdapter<String>  adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, plantas);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  R.layout.text_view2, plantas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                listView1.setAdapter(adapter);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listView1.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                listView2.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                d6.setVisibility(View.VISIBLE);
                c6.setVisibility(View.GONE);
                final String[] plantas = new String[]{"Planta E", "Planta 0", "Planta 1", "Planta 2"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  R.layout.text_view2, plantas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                listView2.setAdapter(adapter);


            }
        });



        listView1.setClickable(true);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detalle sensor");
                Fragment fragment = null;
                String planta  = listView1.getItemAtPosition(position).toString();

                if(planta.equals("Planta 0")) {

                    fragment = new Salad60 ("Planta0","d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                else if(planta.equals("Planta 1")) {

                    fragment = new Salad61 ("Planta1","d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                else if(planta.equals("Planta 2")) {

                    fragment = new Salad62("Planta2","d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }

                else if(planta.equals("Planta S1")) {

                    fragment = new Salad6s1("Planta S1","d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });


        listView2.setClickable(true);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detalle sensor");
                Fragment fragment = null;
                String planta  = listView2.getItemAtPosition(position).toString();


                if(planta.equals("Planta 0")) {

                    fragment = new Salac60 ("Planta 0","c6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                else if(planta.equals("Planta 1")) {

                    fragment = new Salac61 ("Planta1","c6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                else if(planta.equals("Planta 2")) {

                    fragment = new Salac62("Planta2","c6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }

                else if(planta.equals("Planta S1")) {

                    fragment = new Salad6s1("Planta S1","d6");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .addToBackStack(null)
                            .commit();
                }




            }
        });



        super.onActivityCreated(savedInstanceState);
    }

}
