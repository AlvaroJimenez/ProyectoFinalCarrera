package com.example.alvar.tfg;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionBD {

    private Thread sqlThreadGetDevices;
    private Thread sqlThreadAddSensor;
    private Thread sqlThreadDeleteSensor;
    private Thread sqlThreadGetUserData;
    private ProgressDialog proDialog;
    ArrayList<SensorItem> values = new ArrayList<SensorItem>();
    SessionManager session;
    public ConexionBD(final String username, final Context context, final String id)
    {
       final MyCjtSensores mycjtSensores = Cache.getInstance().myCjtSensores;
        final AllCjtSensores allCjtSensores = Cache.getInstance().allCjtSensores;
        sqlThreadGetDevices = new Thread() {
            public void run() {

                Log.i("myAPP", "Conecting...");
                try {
                    Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(
                            "jdbc:postgresql://serviocity.ccapqfkdjz9l.us-west-2.rds.amazonaws.com:5432/datos", "alvaroj", "bolshoibooze12");
                    String stsq1 =null;
                    String stsq2 =null;
                    String stsq3 =null;

                    stsq1 = "select * from ids";

                    stsq2 = "select * from ids i, \"user\" u, user_sensor us\n" +
                            "WHERE i.servioticy_id = us.id_sensor and u.id = us.id_user and u.name = '"+username+"'";

                    stsq3 = "select * from actuators";

                    //creamos la conexion
                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();

                    //primera query
                    ResultSet rs1 = st1.executeQuery(stsq2);

                    System.out.println(stsq1);


                    values = new  ArrayList<SensorItem>();
                    int i = 0;
                    if(mycjtSensores != null) {
                        mycjtSensores.ClearCjt();

                        while (rs1.next()) {

                            mycjtSensores.setCjtSensores(rs1.getString(1), rs1.getString(2), rs1.getString(3), i,rs1.getString(4));
                            i++;
                        }

                    }

                    if(allCjtSensores != null) {

                        System.out.println(stsq2);
                        //segunda query
                        ResultSet rs2 = st2.executeQuery(stsq1);
                        ResultSet rs3 = st1.executeQuery(stsq3);

                        values = new ArrayList<SensorItem>();
                        i = 0;
                        allCjtSensores.ClearCjt();
                        while (rs2.next()) {
                            allCjtSensores.setCjtSensores(rs2.getString(1), rs2.getString(2), rs2.getString(3), i,"s");
                            i++;
                        }

                        while (rs3.next()) {
                            allCjtSensores.setCjtSensores(rs3.getString(1), rs3.getString(2), rs3.getString(3), i,"a");
                            i++;
                        }

                    }

                   // Cache.getInstance().mainActivity.stopServiceMQTTActuators();
                    //Cache.getInstance().mainActivity.startServiceMQTTActuators();


                    conn.close();
                } catch (SQLException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
                }
               //try{ Thread.sleep(5000); }catch(InterruptedException e){ }
            }
        };

        sqlThreadAddSensor = new Thread() {
            public void run() {



                Log.i("myAPP", "Conecting...");
                try {


                    SessionManager session1 = new SessionManager(context);
                    int id_user = session1.getIDUser();


                    Class.forName("org.postgresql.Driver");
                    // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
                    // Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                    Connection conn = DriverManager.getConnection(
                            "jdbc:postgresql://serviocity.ccapqfkdjz9l.us-west-2.rds.amazonaws.com:5432/datos", "alvaroj", "bolshoibooze12");
                    //En el stsql se puede agregar cualquier consulta SQL deseada.
                    String stsq1 =null;
                    stsq1 = "INSERT INTO user_sensor(id_sensor, id_user)\n" +
                            "    VALUES ('"+id+"', "+id_user+");";
                    System.out.println(stsq1.toString());
                    //creamos la conexion
                    Statement st1 = conn.createStatement();
                    //primera query
                    //    st1.executeQuery(stsq1);

                    st1.executeUpdate(stsq1);


                    ConexionBD con = new ConexionBD(username,Cache.getInstance().mainActivity.getApplicationContext(),"");
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

        sqlThreadDeleteSensor = new Thread() {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //try{ Thread.sleep(5000); }catch(InterruptedException e){ }
            }
        };

        sqlThreadGetUserData = new Thread() {
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
                    String stsq2 =null;

                    stsq1 = "SELECT u.id FROM \"user\" u WHERE u.name ='"+username+"'";

                    System.out.print("USERNAME"  + username);

                    session = new SessionManager(context);

                    //creamos la conexion
                    Statement st1 = conn.createStatement();

                    //primera query
                    ResultSet rs1 = st1.executeQuery(stsq1);

                    int i = 0;
                    int id_user = -1;

                    while(rs1.next()) {
                       id_user= Integer.parseInt(rs1.getString(1));
                      //  System.out.print("ID USER1 " + id_user);

                        i++;
                    }
                    session.setKeyUser(id_user);

                  //  System.out.print("ID USER2 " + session.getIDUser());

                    conn.close();
                } catch (SQLException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());
                    try {
                        throw new SQLException();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
                }
                //try{ Thread.sleep(5000); }catch(InterruptedException e){ }
            }
        };


    }

    public void connectGetData()
    {

        sqlThreadGetDevices.start();

    }

    public void connectAdd()
    {

        sqlThreadAddSensor.start();

    }

    public void connectDelete()
    {

        sqlThreadDeleteSensor.start();

    }

    public void getUserData()
    {
        sqlThreadGetUserData.start();

    }





}
