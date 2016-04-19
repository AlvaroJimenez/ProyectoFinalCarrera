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
public class Salad62 extends Fragment {


    private String sala;
    private String planta;
    private String edificio;
    private AllCjtSensores cjtSensores;

    public Salad62(String p, String e) {
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
        View view = inflater.inflate(R.layout.salad62,
                container, false);

        TextView s2 = (TextView)view.findViewById(R.id.s202);
        TextView s3 = (TextView) view.findViewById(R.id.s203);
        TextView s4 = (TextView) view.findViewById(R.id.s204);
        TextView s5 = (TextView) view.findViewById(R.id.s205);
        TextView s6 =  (TextView) view.findViewById(R.id.s206);
        TextView s7 = (TextView) view.findViewById(R.id.s207);
        TextView s8 = (TextView) view.findViewById(R.id.s208);
        TextView s9 = (TextView) view.findViewById(R.id.s209);
        TextView s10 = (TextView) view.findViewById(R.id.s210);
        TextView s11 = (TextView) view.findViewById(R.id.s211);
        TextView s12 = (TextView) view.findViewById(R.id.s212);
        TextView s13 = (TextView) view.findViewById(R.id.s213);
        TextView s14 = (TextView) view.findViewById(R.id.s214);
        TextView s15 = (TextView) view.findViewById(R.id.s215);
        TextView s16 = (TextView) view.findViewById(R.id.s216);


        drawDevice("d6202", s2);
        drawDevice("d6203", s3);
        drawDevice("d6204", s4);
        drawDevice("d6205", s5);
        drawDevice("d6206", s6);
        drawDevice("d6207", s7);
        drawDevice("d6208", s8);
        drawDevice("d6213", s13);
        drawDevice("d6214", s14);
        drawDevice("d6215", s15);
        drawDevice("d6216", s16);






        return view;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
