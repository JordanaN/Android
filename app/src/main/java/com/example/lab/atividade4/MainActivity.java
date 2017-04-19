package com.example.lab.atividade4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab.atividade4.model.User;
import com.example.lab.atividade4.model.helper.UserHelper;


public class MainActivity extends Activity {

    protected Button btnRegistrar;
    protected Button btnLogar;
    protected SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogar = (Button) findViewById(R.id.Loginbutton);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginApp();
            }
        });

        btnRegistrar = (Button) findViewById(R.id.entrarButton);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        prefs = getApplicationContext().getSharedPreferences(
                "session", Context.MODE_PRIVATE);

        if (checkSession())
        {
            homeApp();
        }
    }


    private void loginApp()
    {
        String email = ((EditText) findViewById(R.id.textEmail)).getText().toString();
        String senha = ((EditText) findViewById(R.id.textSenha)).getText().toString();

        if (User.login(email, senha) != null)
        {
            salvarSessao(email);
            homeApp();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Email não cadastrado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarSessao(String email)
    {
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("logged", true);
        editor.putString("email", email);
        editor.commit();
    }

    private void homeApp()
    {
        String email = prefs.getString("email", "");

        if (!email.contentEquals("")) {
            Intent intent = new Intent(this, UserLogadoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("email", email);
            startActivity(intent);

            finish();
        }
    }

    private void registerUser()
    {
        Intent intent = new Intent(this, NovoUserActivity.class);

        startActivity(intent);
    }

    private boolean checkSession()
    {
        return prefs.getBoolean("logged", false);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
