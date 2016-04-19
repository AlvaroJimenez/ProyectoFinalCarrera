package com.example.alvar.tfg;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;
import org.json.JSONObject;

public class MQTTServiceActuator extends Service {

    private static final String TAG = "MQTTServiceSensor";
    private Thread thread;
    private volatile IMqttAsyncClient mqttClient;
    static final String M2MIO_DOMAIN = "Y2VlMTBkYjEtNDVjNi00N2E5LTk0YjEtMzBmOTI0NTUzMzg1YjU2YmE5NzUtOTEyZC00M2FmLWE0MGEtMzJiZjcwNWFkMDg";
    static String[] devices_id = null;
    static int[] qos = null;
    static final String M2MIO_THING = "actions";
    static final String M2MIO_USERNAME = "compose";
    static final String M2MIO_PASSWORD_MD5 = "shines";

    public class MQTTBinder extends Binder {
        public MQTTServiceActuator getService(){
            return MQTTServiceActuator.this;
        }
    }

    @Override
    public void onCreate() {

        try {
            doConnect();
        } catch (MqttException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");
        android.os.Debug.waitForDebugger();
        super.onConfigurationChanged(newConfig);

    }

    public void doConnect() throws MqttException {
        Log.d(TAG, "doConnect()");
        IMqttToken token;
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(M2MIO_USERNAME);
        options.setPassword(M2MIO_PASSWORD_MD5.toCharArray());
        options.setKeepAliveInterval(30);
        try {
            String deviceId = "knowledgeManager";
            mqttClient = new MqttAsyncClient("tcp://api.servioticy.com:1883", deviceId, new MemoryPersistence());
            token = mqttClient.connect(options);
            Log.d(TAG, "Conectado");
            token.waitForCompletion(3500);
            mqttClient.setCallback(new MqttEventCallback());

            if(deviceId != null) {
                for (int i = 0; i < devices_id.length; ++i) {
                    devices_id[i] =  devices_id[i] + "/" + M2MIO_THING;
                }
            }
            token = mqttClient.subscribe(devices_id,qos);
            token.waitForCompletion(5000);
        } catch (MqttSecurityException e) {
            throw e;
        } catch (MqttException e) {
            switch (e.getReasonCode()) {
                case MqttException.REASON_CODE_BROKER_UNAVAILABLE:
                case MqttException.REASON_CODE_CLIENT_TIMEOUT:
                case MqttException.REASON_CODE_CONNECTION_LOST:
                case MqttException.REASON_CODE_SERVER_CONNECT_ERROR:
                    Log.v(TAG, "c" + e.getMessage());
                    throw e;
                case MqttException.REASON_CODE_FAILED_AUTHENTICATION:
                    Intent i = new Intent("RAISEALLARM");
                    i.putExtra("ALLARM", e);
                    throw e;
                default:
                    throw e;
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand()");
        return START_STICKY;
    }

    private class MqttEventCallback implements MqttCallback {

        @Override
        public void connectionLost(Throwable arg0) {

            Log.e(TAG, "Connection Lost ");


        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

            Log.e(TAG, "deliveryComplete");

        }

        @Override
        @SuppressLint("NewApi")
        public void messageArrived(String topic, final MqttMessage msg) throws Exception {
           // Log.i(TAG, "Message arrived from topic" + topic);

            String id = topic.substring(0, 45);
            Log.i(TAG, "ID " + id);
            String sala = Cache.getInstance().allCjtSensores.getUbicacionById(id);
            String tipo = Cache.getInstance().allCjtSensores.getNombreById(id);
            Log.i(TAG, "Sala "+ sala);
            Log.i(TAG, "Tipo "+ tipo);
            Log.i(TAG, "Message arrived from topic" + msg);
            JSONObject json = null;
            String parameters = "-1";
            String descripcion= "-1";
            try{

                if(tipo.equals("HVAC")) {
                    json = new JSONObject(msg.toString());
                    try {
                        parameters = json.getString("parameters").substring(19, 21);
                    }catch (Exception e)
                    {

                    }
                    descripcion = json.getString("description");
                    descripcion = descripcion.substring(16);
                    int a = descripcion.indexOf('"');
                    descripcion = descripcion.substring(0,a);

                }
                else if(tipo.equals("Light"))
                {
                    json = new JSONObject(msg.toString());
                    descripcion = json.getString("description");
                    descripcion = descripcion.substring(16);
                    int a = descripcion.indexOf('"');
                    descripcion = descripcion.substring(0, a);

                }

            Log.i(TAG, "Parameters "+ parameters);
                Log.i(TAG, "Descripcion "+ descripcion);
            }catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(!parameters.equals("-1") && !descripcion.equals("-1") )
                Cache.getInstance().notificationsList.addNotification("Sala "+sala + " " + tipo + " new temperatue " +  parameters +" ºC");
            else if(!descripcion.equals("-1"))
                Cache.getInstance().notificationsList.addNotification("Sala "+sala + " " + tipo + " " + descripcion);

           // Cache.getInstance().notificationsList.addNotification(msg.toString());
            //ActualizarDispositivos(topic, msg);

            //Cache.getInstance().mainActivity.Notification();

        }
    }

    public String getThread(){
        return Long.valueOf(thread.getId()).toString();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind called");
        return null;
    }

    private void ActualizarDispositivos(final String topic, final MqttMessage msg)
    {
        String s = msg.toString();
        MyCjtSensores myCjtSensores = Cache.getInstance().myCjtSensores;

        JSONObject json = null;
        try{

            json = new JSONObject(s);

            String id = topic.substring(topic.indexOf("/"));
            id = id.substring(1, 46);

            String tipo = (myCjtSensores.getNombreById(id));
/*

            if(tipo.equals("XM1000")) {
                Double valueTemp = json.getJSONObject("channels").getJSONObject("temperature").getDouble("current-value");
                Double valueHum = json.getJSONObject("channels").getJSONObject("humidity").getDouble("current-value");

                myCjtSensores.ActualizarDispositivo(id, valueTemp, valueHum);
            }
            else if(tipo.equals("Computer") || tipo.equals("Light"))
            {
                int valor = json.getJSONObject("channels").getJSONObject("computer").getInt("current-value");
                myCjtSensores.ActualizarDispositivo(id,valor);
                System.out.print("VALOR ENCENDIDO "+ valor);
            }
            */

        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

