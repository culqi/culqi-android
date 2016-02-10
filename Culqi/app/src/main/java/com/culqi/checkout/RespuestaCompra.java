package com.culqi.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RespuestaCompra extends AppCompatActivity {

    String respuestaVenta;
    TextView textoRespuesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        textoRespuesa   = (TextView)findViewById(R.id.respuestaVenta);

        Intent intent = getIntent();
        respuestaVenta = intent.getStringExtra("respuesta_venta");

        try {

            cargarFormulario();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {

            e.printStackTrace();
        }

    }

    public void crearNuevaVenta(View view) {

        RespuestaCompra.this.finish();

    }

    public static HttpResponse makeRequest(String uri, String json) {
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            return new DefaultHttpClient().execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cargarFormulario( ) throws IOException, JSONException {

        if (respuestaVenta.equals("checkout_cerrado")) {

            textoRespuesa.setText("El formulario de pago fué cerrado.");

        } else if (respuestaVenta.equals("venta_expirada")) {

            textoRespuesa.setText("La venta ha expirado.");

        } else if (respuestaVenta.equals("error")) {

            textoRespuesa.setText("Ocurrió un error de procesamiento.");

        } else if (respuestaVenta.equals("parametro_invalido")) {

            textoRespuesa.setText("Los parametros enviados son inválidos.");

        } else {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map<String, Object> informacion_pago = new HashMap<String, Object>();
                        informacion_pago.put("respuesta", respuestaVenta);

                        String json = new GsonBuilder().create().toJson(informacion_pago, Map.class);

                        String url = "https://integ-com.culqi.com/respuesta-demo-integracion";

                        HttpResponse response = makeRequest(url, json);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        final String json_response = reader.readLine();

                        System.out.print("Respuesta:" + json_response);

                        if (json_response.equals("venta_exitosa")) {

                            runOnUiThread(new Runnable() {
                                public void run() {

                                    textoRespuesa.setText("");

                                }
                            });

                        } else {

                            runOnUiThread(new Runnable() {
                                public void run() {

                                    textoRespuesa.setText(json_response);

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        }
    }

}
