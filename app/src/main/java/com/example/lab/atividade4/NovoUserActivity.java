package com.example.lab.atividade4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab.atividade4.model.User;
import com.example.lab.atividade4.model.helper.UserHelper;


public class NovoUserActivity extends Activity {

    protected Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_user);

        btnSalvar = (Button) findViewById(R.id.dalvarButton);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NovoUserActivity.this, UserLogadoActivity.class);

                intent.putExtra("email", ((EditText) findViewById(R.id.editTextEmail)).getText().toString());

                NovoUserActivity.this.startActivity(intent);

                User user = new User();

                user.email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
                user.name = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
                user.senha = ((EditText) findViewById(R.id.editTextSenha)).getText().toString();

                UserHelper.INSTANCE.add(user.email, user);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novo_user, menu);
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
