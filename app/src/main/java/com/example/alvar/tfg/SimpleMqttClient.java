package com.example.alvar.tfg;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SimpleMqttClient implements MqttCallback {

    MqttClient myClient;
    MqttConnectOptions connOpt;

    static final String BROKER_URL = "tcp://api.servioticy.com:1883";
    static final String M2MIO_DOMAIN = "Y2VlMTBkYjEtNDVjNi00N2E5LTk0YjEtMzBmOTI0NTUzMzg1YjU2YmE5NzUtOTEyZC00M2FmLWE0MGEtMzJiZjcwNWFkMDg0";
    static final String M2MIO_STUFF = "14519131899457f83b25047f943478f89a71f4d2cac4f'";
    static final String M2MIO_THING = "streams/data/updates";
    static final String M2MIO_USERNAME = "compose";
    static final String M2MIO_PASSWORD_MD5 = "shines";

    // the following two flags control whether this example is a publisher, a subscriber or both
    static final Boolean subscriber = true;
    static final Boolean publisher = true;
    private MqttTopic topic = null;

    /**
     *
     * connectionLost
     * This callback is invoked upon losing the MQTT connection.
     *
     */
    @Override
    public void connectionLost(Throwable t) {
        System.out.println("Connection lost!");
        // code to reconnect to the broker would go here if desired
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

        System.out.println("-------------------------------------------------");
        System.out.println("| Topic:" + topic);
        System.out.println("| Message: " + new String(mqttMessage.getPayload()));
        System.out.println("-------------------------------------------------");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }


    public void deliveryComplete(MqttDeliveryToken token) {
        //System.out.println("Pub complete" + new String(token.getMessage().getPayload()));
    }


    /**
     *
     * MAIN
     *
     */
  /*  public static void main(String[] args) {
        SimpleMqttClient smc = new SimpleMqttClient();
        smc.runClient();
    }
*/

    public void runClient() {
        // setup MQTT Client
        String clientID = "knowledgeManager";
        connOpt = new MqttConnectOptions();

        connOpt.setCleanSession(true);
        //connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(M2MIO_USERNAME);
        connOpt.setPassword(M2MIO_PASSWORD_MD5.toCharArray());

        // Connect to Broker
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            myClient = new MqttClient(BROKER_URL, clientID,persistence);
            myClient.setCallback(this);
            myClient.connect(connOpt);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Connected to " + BROKER_URL);

        // setup topic
        // topics on m2m.io are in the form <domain>/<stuff>/<thing>
        String myTopic = M2MIO_DOMAIN + "/" + M2MIO_STUFF + "/" + M2MIO_THING;
        topic = myClient.getTopic(myTopic);
        Log.d("APP", topic.toString());
        // subscribe to topic if subscriber
        System.out.println("--------Subscribe0--------");
        if (true) {
            try {
                int subQoS = 0;
                System.out.println("--------Subscribe1--------");
                myClient.subscribe(myTopic, subQoS);
                    System.out.println("--------Subscribe2--------");
            } catch (Exception e) {
                System.out.println("CATCH");
                e.printStackTrace();
            }
        }

        // publish messages if publisher
      /*  if (publisher) {
            for (int i=1; i<=10; i++) {
                String pubMsg = "{\"pubmsg\":" + i + "}";
                int pubQoS = 0;
                MqttMessage message = new MqttMessage(pubMsg.getBytes());
                message.setQos(pubQoS);
                message.setRetained(false);

                // Publish the message
                System.out.println("Publishing to topic \"" + topic + "\" qos " + pubQoS);
                MqttDeliveryToken token = null;
                try {
                    // publish message to broker
                    token = topic.publish(message);
                    // Wait until the message has been delivered to the broker
                    token.waitForCompletion();
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/

        // disconnect
        try {
            // wait to ensure subscribed messages are delivered
            if (subscriber) {
               Thread.sleep(5000);
            }
           // myClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected()
    {
        return myClient.isConnected();
    }

    public String getName()
    {
        return topic.toString();
    }
}