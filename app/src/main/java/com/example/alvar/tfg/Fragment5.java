package com.example.alvar.tfg;

import android.R.layout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


/**
 * Created by alvaro on 14/11/2015.
 */
public class Fragment5 extends Fragment {

    private TextView lblMensaje;
    private Spinner cmbOpciones;
    private TextView lblMensaje2;
    private Spinner cmbOpciones2;

    public Fragment5() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment5,
                container, false);


        ListView listview = (ListView) view.findViewById(R.id.listViewNot);
        String[]values = null;

        if(Cache.getInstance().notificationsList.getSize() > 0)
            values = Cache.getInstance().notificationsList.getNombres();
        else {
            values = new String[1];
            values[0] = " ";

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  R.layout.text_view2, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listview.setAdapter(adapter);


        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {





        super.onActivityCreated(savedInstanceState);
    }



}