package com.example.lenovo.sitra;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "REST CLIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkNfcPowerStatus(getApplicationContext()))
            Toast.makeText(this, "Por favor habilite su NFC.", Toast.LENGTH_SHORT).show();

        Button btnRegistrar = (Button) findViewById(R.id.txtRegistrar);
        Button btnIniciar = (Button) findViewById(R.id.btn_iniciar);
        final EditText tbxUsuario = (EditText) findViewById(R.id.tbxUsuario);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            tbxUsuario.setText(extras.getString("usuario"));
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
        final RequestParams params = new RequestParams();
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RestClient.get("users?usuario=" + tbxUsuario.getText().toString(), params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        JSONObject jsonArray = null;
                        try {
                            jsonArray = new JSONObject(response);
                            Intent intent = new Intent(getApplicationContext(), PanelActivity.class);
                            intent.putExtra("usuario",jsonArray.getString("UserName").toString());
                            startActivity(intent);
                        } catch (JSONException e) {
                            Intent intent = new Intent(getApplicationContext(), PanelActivity.class);
                            intent.putExtra("usuario", "Edwin");
                            startActivity(intent);
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });
    }

    public static boolean checkNfcPowerStatus(Context context) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        boolean enabled = false;

        if (nfcAdapter != null) {
            enabled = nfcAdapter.isEnabled();
        }

        return enabled;
    }
}
