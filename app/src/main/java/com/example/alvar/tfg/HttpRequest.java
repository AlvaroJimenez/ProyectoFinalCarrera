package com.example.alvar.tfg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by alvar on 29/11/2015.
 */
 public class HttpRequest extends AsyncTask<String,String,String> {

    static final String M2MIO_DOMAIN = "Y2VlMTBkYjEtNDVjNi00N2E5LTk0YjEtMzBmOTI0NTUzMzg1YjU2YmE5NzUtOTEyZC00M2FmLWE0MGEtMzJiZjcwNWFkMDg0";
    private String M2MIO_STUFF = null;
    private  ProgressBar progresbar;
    private ProgressDialog proDialog;
    private MainActivity mainActivity;
    private ControladorListaDispositivosSala controladorListaDispositivosSala;
    private String tipo =null;
    private final String LOG_TAG = HttpRequest.class.getSimpleName();
    static boolean done = false;
    private Exception exceptionToBeThrown;
    CircularProgressView progressView;

    public HttpRequest(String id,MainActivity activity,String nombre)
    {
        super();
        M2MIO_STUFF = id;
        this.mainActivity = activity;
        this.tipo = nombre;

    }

    @Override
    protected String doInBackground(String... params) {

       // try{ Thread.sleep(5000); }catch(InterruptedException e){ }

        System.out.println("HTTP REQUESTTTT");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonResponse = null;
        URL url = null;
        try {
            url = new URL("http://api.servioticy.com/" + URLEncoder.encode(M2MIO_STUFF, "UTF-8") + "/streams/data/lastUpdate");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", M2MIO_DOMAIN);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = null;
            inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                //nothing to do
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            // there is an opened connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (buffer.length() == 0) {
                // buffer empty, no point in parsing
                return null;
            }
            jsonResponse = buffer.toString();
        }catch (Exception e){

            exceptionToBeThrown = e;
        }


        return jsonResponse;
    }

    protected void onPostExecute(String s) {

        if (exceptionToBeThrown != null) try {
            throw exceptionToBeThrown;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s != null)
        {
            try {

                System.out.println("JSON: " + s.toString() +" " + tipo);
                JSONObject json = null;
                json = new JSONObject(s);
                JSONArray jsonarray = json.getJSONArray("data");
                JSONObject data0 = jsonarray.getJSONObject(0);

                if (tipo.equals("XM1000")) {
                    Double valueTemp = data0.getJSONObject("channels").getJSONObject("temperature").getDouble("current-value");
                    Double valueHum = data0.getJSONObject("channels").getJSONObject("humidity").getDouble("current-value");
                  //  int pos = Cache.getInstance().myCjtSensores.getPosById(M2MIO_STUFF);
                    Cache.getInstance().myCjtSensores.ActualizarDispositivo(M2MIO_STUFF, valueTemp, valueHum);
                } else if (tipo.equals("Computer")) {
                    int valor = data0.getJSONObject("channels").getJSONObject("computer").getInt("current-value");
                    Cache.getInstance().myCjtSensores.ActualizarDispositivo(M2MIO_STUFF, valor);
                }
                else if(tipo.equals("Light"))
                {
                    int valor = data0.getJSONObject("channels").getJSONObject("light").getInt("current-value");
                    Cache.getInstance().myCjtSensores.ActualizarDispositivo(M2MIO_STUFF, valor);
                }
                else if(tipo.equals("Presence"))
                {
                    int valor = data0.getJSONObject("channels").getJSONObject("presence").getInt("current-value");
                    Cache.getInstance().myCjtSensores.ActualizarDispositivo(M2MIO_STUFF, valor);
                }
                else if(tipo.equals("AirQuality"))
                {
                    int valor = data0.getJSONObject("channels").getJSONObject("airquality").getInt("current-value");
                    Cache.getInstance().myCjtSensores.ActualizarDispositivo(M2MIO_STUFF, valor);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
      //  proDialog.dismiss();

    }
    @Override
    protected void onPreExecute() {

      /*  proDialog = new ProgressDialog(mainActivity);
        proDialog.setTitle("App name");
        proDialog.setMessage("Loding...");
        proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //proDialog.setIcon(R.drawable.)
        proDialog.setCancelable(true);
        proDialog.show();*/
        progressView = new CircularProgressView(mainActivity);
      //  progressView = (CircularProgressView) mainActivity.findViewById(R.id.progress_view);
        progressView.startAnimation();
        super.onPreExecute();
    }

}
