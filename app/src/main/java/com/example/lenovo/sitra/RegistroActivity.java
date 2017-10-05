package com.example.lenovo.sitra;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

public class RegistroActivity extends AppCompatActivity {

    private static final String TAG = "REST CLIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        final EditText tbxNombre = (EditText) findViewById(R.id.txtNombreCompleto);
        final EditText tbxUsuario = (EditText) findViewById(R.id.txtUsuario);
        final EditText tbxContrasenia = (EditText) findViewById(R.id.txtContrasenia);
        final RequestParams params = new RequestParams();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params.put("Nombre", tbxNombre.getText().toString());
                params.put("Activo", true);
                params.put("UserName", tbxUsuario.getText().toString());
                params.put("Password", tbxContrasenia.getText().toString());
                params.put("Cedula", "00000000");
                params.put("RoleId", 2);

                RestClient.post("users", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        JSONObject jsonArray = null;
                        try {
                            jsonArray = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), "Usuario creado correctamente.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("usuario", tbxUsuario.getText().toString());
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "El usuario no pudo ser creado.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
