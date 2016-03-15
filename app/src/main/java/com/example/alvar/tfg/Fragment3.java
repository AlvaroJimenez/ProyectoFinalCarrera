package com.example.alvar.tfg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class Fragment3 extends Fragment {


    GridView gridView;
    int position;

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        final GridView gridView = (GridView) getView().findViewById(R.id.grid);

        gridView.setAdapter(new AdaptadorDeSensores(getView().getContext()));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
           public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                System.out.println("position "+ position);


                String pos = String.valueOf(position);
                Log.d("myAct", pos);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detalle sensor");
                Fragment fragment = null;
                fragment = new Fragment5();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);




//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Intent i = new Intent(getApplicationContext(),DetalleSensor.class);
//                startActivity(i);
//
//            }
//        });

        return view;



        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }
}


