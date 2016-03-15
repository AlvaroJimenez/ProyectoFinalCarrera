package com.example.alvar.tfg;

import android.text.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvar on 01/11/2015.
 */
public class AllCjtSensores {


    ArrayList<SensorItem> ListSensors = new ArrayList<SensorItem>();
    public AllCjtSensores()
    {
        ListSensors = new ArrayList<SensorItem>();

    }

    public void setCjtSensores(String id, String nombre, String ubicacion, int i,String tipology)
    {
        String resultU = ubicacion.substring(ubicacion.lastIndexOf("/") + 1);
        SensorItem item = new SensorItem(id,nombre,resultU,tipology);
        ListSensors.add(i,item);

    }



    public String[] getNombres()
    {
        String[] values= new String[ListSensors.size()];

        for(int i=0;i<ListSensors.size();++i)
            values[i] = String.valueOf((Html.fromHtml(ListSensors.get(i).getNombre() + "\n" + ListSensors.get(i).getUbicacion())));
        // values[i] = ListSensors.get(i).getNombre() + " " +ListSensors.get(i).getUbicacion();

        return  values;
    }

    public String[] getNombresBySala(String sala)
    {
        int size = 0;
        for(int i=0;i<ListSensors.size();++i) {
            if (ListSensors.get(i).getUbicacion().equals(sala))
                size++;

        }

        String[] values= new String[size];
        int j = 0;
        for(int i=0;i<ListSensors.size();++i) {
            if(ListSensors.get(i).getUbicacion().equals(sala)) {
                values[j] = String.valueOf((Html.fromHtml(ListSensors.get(i).getNombre() + "\n" + ListSensors.get(i).getUbicacion())));
                ++j;
            }

        }

        return  values;
    }

    public String[] getIds(String sala, int size)
    {
        String[] values= new String[size];
        int j = 0;
        for(int i=0;i<ListSensors.size();++i) {
            values[j] = ListSensors.get(i).getId();
            ++j;
        }

        return values;
    }

    public String[] getIdBySala(String sala, int size)
    {
        String[] values= new String[size];
        int j = 0;
        for(int i=0;i<ListSensors.size();++i) {
            if(ListSensors.get(i).getUbicacion().equals(sala) || sala.equals(" ")) {
                values[j] = ListSensors.get(i).getId();
                ++j;
            }
        }

        return values;
    }

    public String getNombre(int i)
    {
        return ListSensors.get(i).getNombre();
    }

    public String getUbicacion(int i)
    {
        return ListSensors.get(i).getUbicacion();
    }

    public Double getTemperatura(int i) {return ListSensors.get(i).getTemperatura();}

    public int getPosById(String id)
    {
        boolean found = false;
        int pos = -1;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getId().equals(id)) {
                pos = i;
                found = true;
            }
        }
        return pos;

    }
    public String getNombreById(String id)
    {
        boolean found = false;
        String nombre = null;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getId().equals(id)) {
                nombre = ListSensors.get(i).getNombre();
                found = true;
            }
        }
        return nombre;

    }

    public String getUbicacionById(String id)
    {
        boolean found = false;
        String ubi = null;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getId().equals(id)) {
                ubi = ListSensors.get(i).getUbicacion();
                found = true;
            }
        }
        return ubi;

    }

    public Double getTemperaturaById(String id)
    {
        boolean found = false;
        Double temp = null;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getId().equals(id)) {
                temp = ListSensors.get(i).getTemperatura();
                found = true;
            }
        }
        return temp;

    }

    public String getHumedadById(String id)
    {
        boolean found = false;
        String temp = null;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getId().equals(id)) {
                temp = ListSensors.get(i).getHumedad().toString();
                found = true;
            }
        }
        return temp;

    }

    public void updateSensor(int i,Double temp,Double hum)
    {
        ListSensors.get(i).setTemperatura(temp);
        ListSensors.get(i).setHumedad(hum);

    }

    public int getSize()
    {
        return ListSensors.size();
    }

    public String getIDByPos(int i)
    {
        SensorItem s = ListSensors.get(i);
        return s.getId();
    }

    public String getNombreByPos(int i)
    {
        SensorItem s = ListSensors.get(i);
        return s.getNombre();
    }

    public String getTypeByPos(int i)
    {
        SensorItem s = ListSensors.get(i);
        return s.getType();
    }

    public boolean existSensorBySala(String sala)
    {
        boolean found = false;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getUbicacion().equals(sala))
                found = true;

        }
        return found;

    }

    public SensorItem getSensorItemById(String id)
    {
        SensorItem sensorItem = null;
        Boolean found = false;

        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            if(ListSensors.get(i).getId().equals(id)){
                found = true;
                sensorItem = ListSensors.get(i);

            }

        }
        return sensorItem;
    }

    public boolean existDevice(String name, String sala)
    {
        boolean found = false;
        for(int i=0;i<ListSensors.size() && !found;++i)
        {
            found = (ListSensors.get(i).getNombre().equals(name) && ListSensors.get(i).getUbicacion().equals(sala));
        }
        return found;
    }

    public void ActualizarDispositivo(String id, int valor){

        boolean found = false;
        for(int i=0;i<ListSensors.size() && !found;++i) {
            if(ListSensors.get(i).getId().equals(id))
            {
                found = true;
                ListSensors.get(i).setValue(valor);

            }

        }
    }
    public void ClearCjt() {
        ListSensors.clear();
    }

    public boolean existTypeSensorBySala(String sala)
    {
        boolean found = false;
        for(int i=0;i<ListSensors.size() && !found;++i) {
           found = ((ListSensors.get(i).getUbicacion().equals(sala)) && ListSensors.get(i).getType().equals("s"));

        }
        return found;
    }


    public boolean existTypeActuatorBySala(String sala)
    {
        boolean found = false;
        for(int i=0;i<ListSensors.size() && !found;++i) {
            found = ((ListSensors.get(i).getUbicacion().equals(sala)) && ListSensors.get(i).getType().equals("a"));

        }
        return found;

    }

    public String actuatorIdBySala(String sala, String name)
    {
        String idreturn = " ";
        for(int i=0;i<ListSensors.size();++i) {
            if(ListSensors.get(i).getType().equals("a") && ListSensors.get(i).getNombre().equals(name) && ListSensors.get(i).getUbicacion().equals(sala))
                return ListSensors.get(i).getId();

        }
        return idreturn;
    }

    public int numberOfActuators()
    {
        int number=0;
        for(int i=0;i<ListSensors.size();++i) {
            if (ListSensors.get(i).getType().equals("a"))
                number++;
        }
        return number;

    }






}
