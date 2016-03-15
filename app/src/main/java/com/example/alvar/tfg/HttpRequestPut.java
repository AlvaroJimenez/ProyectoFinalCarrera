package com.example.alvar.tfg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by alvar on 29/11/2015.
 */
public class HttpRequestPut extends AsyncTask<String, String, Integer> {

    static final String M2MIO_DOMAIN = "Y2VlMTBkYjEtNDVjNi00N2E5LTk0YjEtMzBmOTI0NTUzMzg1YjU2YmE5NzUtOTEyZC00M2FmLWE0MGEtMzJiZjcwNWFkMDg0";
    private String M2MIO_STUFF = null;
    private MyCjtSensores cjtSensores;
    private  ProgressBar progresbar;
    private ProgressDialog proDialog;
    private MainActivity mainActivity;
    private ControladorListaDispositivosSala controladorListaDispositivosSala;
    private String tipo;
    private int input;
    private int status;
    private final String LOG_TAG = HttpRequest.class.getSimpleName();
    public static boolean done = false;

    public HttpRequestPut(String id,MainActivity activity,String tipo,int status, int input)
    {
        super();
        M2MIO_STUFF = id;
        this.mainActivity = activity;
        this.tipo = tipo;
        this.input = input;
        this.status = status;

    }

    @Override
    protected Integer doInBackground(String... params) {

        Integer result = 0;
            try{

                System.out.println("ACTUACION");

                OutputStream os = null;
                InputStream is = null;
                HttpURLConnection conn = null;

                URL url = null;

                JSONObject json = null;
                url = new URL("http://api.servioticy.com/"+ URLEncoder.encode(M2MIO_STUFF, "UTF-8")+"/actuations/switch_on/");

                String message = " ";


                    if(status == 1 && input == 0)
                        url = new URL("http://api.servioticy.com/"+ URLEncoder.encode(M2MIO_STUFF, "UTF-8")+"/actuations/switch_on/");
                    else if(status == 0 && input == 0)
                        url = new URL("http://api.servioticy.com/"+ URLEncoder.encode(M2MIO_STUFF, "UTF-8")+"/actuations/switch_off/");
                    else if (status == 1 && input != 0)
                    {
                        url = new URL("http://api.servioticy.com/"+ URLEncoder.encode(M2MIO_STUFF, "UTF-8")+"/actuations/switch_on/");
                         message = "{\"new_temperature\"=" + input + "}";
                    }



                System.out.println(M2MIO_STUFF);
                System.out.println(url.toString());


            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /*milliseconds*/);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Authorization",M2MIO_DOMAIN);

            //open
            conn.connect();

            //setup send
             os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            //clean up
            os.flush();
            os.close();
            conn.disconnect();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        }

    @Override
    protected void onPostExecute(Integer s) {

      //  UpdateDevice.getInstance().execute();
        proDialog.dismiss();

    }
    @Override
    protected void onPreExecute() {


        done = true;
        proDialog = new ProgressDialog(mainActivity);
        proDialog.setTitle("App name");
        proDialog.setMessage("Loding...");
        proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //proDialog.setIcon(R.drawable.)
        proDialog.setCancelable(true);
        proDialog.show();

        super.onPreExecute();
    }

}
