package com.example.alvar.tfg;

/**
 * Created by alvar on 29/09/2015.
 */
public class Sensor {

    private int id;
    private String nombre;
    private int idDrawable;

    public Sensor(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static Sensor[] ITEMS = {
            new Sensor("Luz",R.drawable.light),
            new Sensor("Temperatura", R.drawable.temperature),
            new Sensor("PC", R.drawable.pc),
            new Sensor("Sensor1", R.drawable.device),
            new Sensor("Actuador1", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),
            new Sensor("Sensor2", R.drawable.device),

    };


    public static Sensor getItem(int id)
    {
        for (Sensor item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    //CONSULTORAS



}
