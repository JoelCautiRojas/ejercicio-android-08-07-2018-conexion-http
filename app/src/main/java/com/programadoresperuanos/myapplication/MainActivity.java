package com.programadoresperuanos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Button boton1;
    TextView p1,p2,p3,p4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1 = findViewById(R.id.textView4);
        p2 = findViewById(R.id.textView5);
        p3 = findViewById(R.id.textView6);
        p4 = findViewById(R.id.textView7);
        boton1 = findViewById(R.id.button);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });
        actualizar();

    }

    public void actualizar()
    {
        AsyncHttpClient micliente = new AsyncHttpClient();
        micliente.post("http://www.programadoresperuanos.com/test_app/index.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    try {
                        JSONObject mirespuesta = new JSONObject(String.valueOf(responseBody));
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            JSONArray matrizPaises = new JSONArray(mirespuesta);
                            p1.setText(matrizPaises.getString(0));
                            p2.setText(matrizPaises.getString(1));
                            p3.setText(matrizPaises.getString(2));
                            p4.setText(matrizPaises.getString(3));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
