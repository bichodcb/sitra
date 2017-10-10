package com.example.lenovo.sitra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.RequestParams;

public class PagarActivity extends FragmentActivity {
    public String usuario;
    public String codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CardEmulationFragment fragment = new CardFragment();
        transaction.replace(R.id.card_content_fragment, fragment);
        transaction.commit();
        
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final RequestParams params = new RequestParams();

        if (extras != null) {
            usuario = extras.getString("usuario");
            codigo = extras.getString("codigo");
            String url = "transferencias?codigo=" + codigo + "&monto=10&deposito=0";
//            RestClient.get(url, params, new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    String response = new String(responseBody);
//                    JSONObject jsonArray = null;
//                    try {
//                        jsonArray = new JSONObject(response);
//                        Toast.makeText(PagarActivity.this, "Se depositó 10 bs.", Toast.LENGTH_SHORT).show();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(PagarActivity.this, "ERROR JSON.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    Toast.makeText(PagarActivity.this, "ERROR SERVER.", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

        Button verSaldo = (Button) findViewById(R.id.btnSaldo);

        verSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PanelActivity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });
    }
}
