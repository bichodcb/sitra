package com.example.lenovo.sitra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PanelActivity extends AppCompatActivity {
    public String usuario;
    public String codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        final TextView tbxSaldo = (TextView) findViewById(R.id.tbxSaldo);
        Button btnPagar = (Button) findViewById(R.id.btnPagar);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final RequestParams params = new RequestParams();

        if (extras != null) {
            usuario = extras.getString("usuario");
            RestClient.get("users?usuario=" + usuario, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String response = new String(responseBody);
                    JSONObject jsonArray = null;
                    try {
                        jsonArray = new JSONObject(response);
                        Toast.makeText(PanelActivity.this, "Bienvenido: " + jsonArray.getString("Nombre"), Toast.LENGTH_SHORT).show();
                        codigo = jsonArray.getString("CodigoUnico");
                        tbxSaldo.setText("Saldo: " + jsonArray.getString("Saldo") + " Bs.");

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(PanelActivity.this, "ERROR.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(PanelActivity.this, "ERROR.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PagarActivity.class);
                intent.putExtra("usuario", usuario);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }
        });
    }
}
