package com.example.alvar.tfg;

import android.app.ProgressDialog;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alvar on 26/10/2015.
 */
public class EliminarSensorDB {



    private Thread sqlThread;
    private ProgressDialog proDialog;
    ArrayList<SensorItem> values = new ArrayList<SensorItem>();
    SessionManager session;
    public EliminarSensorDB(final String username,final String id)
    {
        sqlThread = new Thread() {
            public void run() {



                Log.i("myAPP", "Conecting...");
                try {
                    Class.forName("org.postgresql.Driver");
                    // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
                    // Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                    Connection conn = DriverManager.getConnection(
                            "jdbc:postgresql://serviocity.ccapqfkdjz9l.us-west-2.rds.amazonaws.com:5432/datos", "alvaroj", "bolshoibooze12");
                    //En el stsql se puede agregar cualquier consulta SQL deseada.
                    String stsq1 =null;
                    stsq1 = "DELETE FROM user_sensor us WHERE us.id_sensor IN (SELECT us.id_sensor FROM user_sensor us, \"user\" u WHERE us.id_user = u.id and u.name = '"+username+"' and  us.id_sensor = '"+id+"')";
                    System.out.println(stsq1.toString());
                    //creamos la conexion
                    Statement st1 = conn.createStatement();
                    //primera query
                 //    st1.executeQuery(stsq1);

                    st1.executeUpdate(stsq1);


                    ConexionBD con = new ConexionBD(username,null,"");
                    con.connectGetData();



                    conn.close();
                } catch (SQLException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
                }
                //try{ Thread.sleep(5000); }catch(InterruptedException e){ }
            }
        };

        //   proDialog.dismiss();

    }

    public void connect()
    {

        sqlThread.start();

    }





}
