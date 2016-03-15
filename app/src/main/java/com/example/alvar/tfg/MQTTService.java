package com.example.alvar.tfg;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MQTTService extends Service {

    private static final String TAG = "MQTTService";
    private Thread thread;
    private volatile IMqttAsyncClient mqttClient;    //static final String deviceId = "1448269986858f7565ccf33ff41c1991afc313754a146";
    static final String BROKER_URL = "tcp://api.servioticy.com:1883";
    static final String M2MIO_DOMAIN = "Y2VlMTBkYjEtNDVjNi00N2E5LTk0YjEtMzBmOTI0NTUzMzg1YjU2YmE5NzUtOTEyZC00M2FmLWE0MGEtMzJiZjcwNWFkMDg0";
    static String[] devices_id = null;
  //  static String device_id;
    static int[] qos = null;
    static final String M2MIO_THING = "streams/data/updates";
    static final String M2MIO_USERNAME = "compose";
    static final String M2MIO_PASSWORD_MD5 = "shines";

    public class MQTTBinder extends Binder {
        public MQTTService getService(){
            return MQTTService.this;
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
            //String myTopic = M2MIO_DOMAIN + "/" + device_id + "/" + M2MIO_THING;
            if(deviceId != null) {
                for (int i = 0; i < devices_id.length; ++i) {
                    devices_id[i] = M2MIO_DOMAIN + "/" + devices_id[i] + "/" + M2MIO_THING;
                }
            }

            token = mqttClient.subscribe(devices_id,qos);

           // Log.d(TAG, "Subscrito " + devices_id[0] + " "+ devices_id[1] + " "+ devices_id[2] + " ...");
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
            Log.i(TAG, "Message arrived from topic" + topic);
            ActualizarDispositivos(topic, msg);

            Cache.getInstance().mainActivity.Notification();





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

        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

