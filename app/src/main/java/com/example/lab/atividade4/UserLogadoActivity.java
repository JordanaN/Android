package com.example.lab.atividade4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lab.atividade4.model.User;
import com.example.lab.atividade4.model.helper.UserHelper;


public class UserLogadoActivity extends Activity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logado);

        prefs = getApplicationContext().getSharedPreferences(
                "session", Context.MODE_PRIVATE);

        String email_logged = getIntent().getStringExtra("email");

        if (email_logged.contentEquals("")) {
            Intent intent = new Intent(this, MainActivity.class);

            resetSession();

            startActivity(intent);
        } else {
            ((TextView) findViewById(R.id.labelNameUserLog)).setText(email_logged);
        }

        /*User user = UserHelper.INSTANCE.find(email_logged);

        if (user == null)
        {
            Intent intent = new Intent(this, MainActivity.class);

            resetSession();

            startActivity(intent);
        }
        else
        {
            ((TextView) findViewById(R.id.labelNameUserLog)).setText(user.nome);
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_logado, menu);
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

    private void resetSession()
    {
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("logged", false);
        editor.putString("email", null);
        editor.commit();
    }
}
