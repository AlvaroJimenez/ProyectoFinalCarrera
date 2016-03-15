package com.example.alvar.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText pass;
    SessionManager session;
    AlertDialogManager alert = new AlertDialogManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        email = (EditText)findViewById(R.id.input_email);
        pass = (EditText)findViewById(R.id.input_password);

        session = new SessionManager(getApplicationContext());

        System.out.println("ID USER "+session.getIDUser());

        final Button button = (Button) findViewById(R.id.btn_login);


      /*  if (session.getIDUser() > -1) {
            session.createLoginSession(session.getUsername(), "anroidhive@gmail.com");
            Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(myIntent);
        }*/

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String username = email.getText().toString();
                String password = pass.getText().toString();


               // Toast.makeText(v.getContext(), "Hello", Toast.LENGTH_LONG).show();
                if(username.trim().length() > 0 && password.trim().length() > 0)
                {
                    ConexionBD con= new ConexionBD(username.toString(),getApplicationContext(),"");
                    con.getUserData();
                    try{ Thread.sleep(1000); }catch(InterruptedException e){ }
                    System.out.println("ID USER "+session.getIDUser());
                    if (session.getIDUser() > -1) {
                        con.connectGetData();
                     //   try{ Thread.sleep(1000); }catch(InterruptedException e){ }
                        session.createLoginSession(username.toString(), "anroidhive@gmail.com");
                        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                        // myIntent.putExtra("key", value); //Optional parameters
                        startActivity(myIntent);
                    }
                    else Toast.makeText(getApplication(), "El usuario no se encuentra en el sistema",
                                Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(getApplication(), "Usuario o contraseña incorrecta",
                        Toast.LENGTH_LONG).show();
                }
        });
    }
    // alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loggin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
