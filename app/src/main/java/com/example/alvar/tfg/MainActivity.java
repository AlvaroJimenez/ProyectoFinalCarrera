package com.example.alvar.tfg;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Activity myAct = this;
    private  SimpleMqttClient smc;
    private MyCjtSensores MySensors = null;
    private AllCjtSensores AllSensors = null;
    private NotificationsList notificationsList = null;
    private String[] values;
    private String username;
    private ProgressDialog proDialog;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

        session = new SessionManager(getApplicationContext());

        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();

        // name
        username = user.get(SessionManager.KEY_NAME);

        MySensors = new MyCjtSensores();
        AllSensors = new AllCjtSensores();
        notificationsList = new NotificationsList();


        Cache.getInstance().myCjtSensores = MySensors;
        Cache.getInstance().allCjtSensores = AllSensors;
        Cache.getInstance().mainActivity = this;
        Cache.getInstance().notificationsList = notificationsList;



        //Eventos del Drawer Layout
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        TextView userlabel = (TextView)findViewById(R.id.user);
        userlabel.setText(username);
        navView = (NavigationView)findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                fragment = new SeleccionarEdificio(AllSensors);
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                try {UpdateDevice.getInstance().execute();
                                }catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}
                                fragment = new ControladorListaMisDispositivos(" ", username);
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new Fragment5();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_opcion_1:
                               // Intent myIntent = new Intent(getBaseContext(), LoginActivity.class);
                              //  startActivity(myIntent);
                                session.logoutUser();
                                break;
                            case R.id.menu_opcion_2:
                                Intent graf = new Intent(getBaseContext(), SimpleXYPlotActivity.class);
                                startActivity(graf);
                                break;
                        }


                        if (fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .addToBackStack(null)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());


                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

        boolean fragmentTransaction2 = false;
        Fragment fragment = null;
        String menuFragment = getIntent().getStringExtra("menuFragment");
        if(menuFragment != null && menuFragment.equals("true"))
        {

            fragment = new ControladorListaDispositivosSala(" ", username);
            fragmentTransaction2 = true;

        }


        if (fragmentTransaction2) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();

            getSupportActionBar().setTitle("Mis dispositivos");
        }

/*
        proDialog = new ProgressDialog(this);
        proDialog.setTitle("App name");
        proDialog.setMessage("Loding...");
        proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //proDialog.setIcon(R.drawable.)
        proDialog.setCancelable(true);
        proDialog.show();
*/

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        ConexionBD con = null;
        try {
            con = new ConexionBD(username,this,"");
            con.connectGetData();

        }catch(Exception e)
        {
            Toast.makeText(myAct, e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

       try{ Thread.sleep(5000); }catch(InterruptedException e){ }

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);


        try {
     //       UpdateDevice.getInstance().execute();

        }catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}

        //SUSCRIPCION SENSORES

      /*  String[]devices_id = new String[MySensors.getSize()];
        int[] qos = new int[MySensors.getSize()];
        try
        {
            for (int i = 0; i < MySensors.getSize(); ++i)
            {
                 devices_id[i] = MySensors.getIDByPos(i);
                 qos[i] = 0;
            }
        }catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}

        MQTTServiceSensor.devices_id = devices_id;
        MQTTServiceSensor.qos = qos;
        try {
            startService(new Intent(this, MQTTServiceSensor.class));
        }catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}*/

        startServiceMQTTActuators();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTitulo(String t)
    {
        getSupportActionBar().setTitle(t);
    }

    public void setConexion()
    {

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            //getSupportActionBar().setTitle(menuItem.getTitle());
        } else {
        }
    }

    public void Notification()
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setLargeIcon((((BitmapDrawable)getResources()
                                .getDrawable(R.drawable.icon)).getBitmap()))
                        .setContentTitle("Mensaje de Alerta")
                        .setContentText("Ejemplo de notificación.")
                        .setContentInfo("4")
                        .setTicker("Alerta!");

        Intent notIntent =
                new Intent(MainActivity.this, MainActivity.class);

        notIntent.putExtra("menuFragment","true");

        PendingIntent contIntent =
                PendingIntent.getActivity(
                        MainActivity.this, 0, notIntent, 0);

        mBuilder.setContentIntent(contIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());
    }

    public void startServiceMQTTActuators()
    {
        //SUSCRIPCION ACTUATORS
        String[] devices_id_actuators = new String[Cache.getInstance().allCjtSensores.numberOfActuators()];
        int[] qos_actuators = new int[Cache.getInstance().allCjtSensores.numberOfActuators()];
        try
        {
            int j=0;
            for (int i = 0; i < Cache.getInstance().allCjtSensores.getSize();++i)
            {
                if(Cache.getInstance().allCjtSensores.getTypeByPos(i).equals("a")) {
                    devices_id_actuators[j] = Cache.getInstance().allCjtSensores.getIDByPos(i);
                    qos_actuators[j] = 0;
                    ++j;
                }
            }
        }
        catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}
        MQTTServiceActuator.devices_id = devices_id_actuators;
        MQTTServiceActuator.qos = qos_actuators;


        try {
            System.out.println("START MQTT ACTUATOR SERVICE");
            startService(new Intent(this, MQTTServiceActuator.class));
        }catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}

    }

    public void stopServiceMQTTActuators()
    {
        try {
            System.out.println("STOP MQTT ACTUATOR SERVICE");
            stopService(new Intent(this, MQTTServiceActuator.class));
        }catch(Exception e) {Toast.makeText(myAct, e.getMessage(),Toast.LENGTH_SHORT).show();}

    }

}
