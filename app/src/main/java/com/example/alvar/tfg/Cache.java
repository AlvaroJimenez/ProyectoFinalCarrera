package com.example.alvar.tfg;

/**
 * Created by alvar on 06/01/2016.
 */
public class Cache {

    public MyCjtSensores myCjtSensores;
    public AllCjtSensores allCjtSensores;
    public MainActivity mainActivity;
    public NotificationsList notificationsList;
    private static Cache ourInstance = new Cache();


    public static Cache getInstance() {
        return ourInstance;
    }

    private Cache() {
    }
}
