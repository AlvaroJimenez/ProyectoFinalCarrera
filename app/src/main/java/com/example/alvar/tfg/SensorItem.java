package com.example.alvar.tfg;

/**
 * Created by alvar on 07/11/2015.
 */
public class SensorItem {

    private String id;
    private String nombre;
    private String ubicación;
    private Double temperatura;
    private Double humedad;
    private int value;
    private String type;

    public SensorItem(String id, String nombre, String ubicación,String tipology)
    {
        this.id = id;
        this.nombre = nombre;
        this.ubicación = ubicación;
        this.temperatura = 0.0;
        this.humedad = 0.0;
        this.value = 0;
        this.type = tipology;
    }

    public void setNombre(String nombre){

        this.nombre = nombre;
    }

    public void setId(String id){

        this.id = id;
    }
    public void setUbicacion(String ubicación)
    {
        this.ubicación = ubicación;

    }

    public String getNombre(){

        return nombre;
    }

    public String getId(){

        return id;
    }
    public String getUbicacion() {
        return ubicación;

    }


    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
