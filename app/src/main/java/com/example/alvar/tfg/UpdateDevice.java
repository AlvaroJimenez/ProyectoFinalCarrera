package com.example.alvar.tfg;

import android.widget.Toast;

/**
 * Created by alvar on 20/01/2016.
 */
public class  UpdateDevice {


    private static UpdateDevice ourInstance = new UpdateDevice();


    public static UpdateDevice getInstance() {
        return ourInstance;
    }


    public void execute() throws Exception
    {

        try {
            for (int i = 0; i < Cache.getInstance().myCjtSensores.getSize(); ++i) {
              //  if (Cache.getInstance().myCjtSensores.getNombreByPos(i).equals("XM1000")) {
                    HttpRequest http = new HttpRequest(Cache.getInstance().myCjtSensores.getIDByPos(i), Cache.getInstance().mainActivity, Cache.getInstance().myCjtSensores.getNombreByPos(i));
                    http.execute();
                //}
            }
            for (int i = 0; i < Cache.getInstance().allCjtSensores.getSize(); ++i) {
                System.out.println("all sensors " + i);
                if (Cache.getInstance().allCjtSensores.getTypeByPos(i).equals("s")){
                    HttpRequestAllDevices http2 = new HttpRequestAllDevices(Cache.getInstance().allCjtSensores.getIDByPos(i), Cache.getInstance().mainActivity, Cache.getInstance().allCjtSensores.getNombreByPos(i));
                    http2.execute();
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
