package com.culqi.checkout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SecureRandom random = new SecureRandom();

    EditText nombres;
    EditText apellidos;
    EditText ciudad;
    EditText pais;
    EditText telefono;
    EditText direccion;
    EditText correo;
    EditText monto;

    CheckBox terminos;

    public String randomOrder() {
        return new BigInteger(16, random).toString(4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres   = (EditText)findViewById(R.id.nombre);
        apellidos   = (EditText)findViewById(R.id.apellido);
        ciudad   = (EditText)findViewById(R.id.ciudad);
        pais   = (EditText)findViewById(R.id.pais);
        telefono   = (EditText)findViewById(R.id.telefono);
        direccion   = (EditText)findViewById(R.id.direccion);
        correo   = (EditText)findViewById(R.id.correo);
        monto   = (EditText)findViewById(R.id.monto);

        terminos   = (CheckBox)findViewById(R.id.terminos);

        nombres.setText("Carlos");
        apellidos.setText("Morales");
        ciudad.setText("Lima");
        pais.setText("PE");
        telefono.setText("991324703");
        direccion.setText("Avenida Arequipa 1155");
        correo.setText("carlos.morales@culqi.com");

    }

    public static void execute() {

    }


    public static HttpResponse makeRequest(String uri, String json) {
       try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
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


   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){

                String stredittext=data.getStringExtra("respuesta_venta");

                Context context = getApplicationContext();
                CharSequence text = stredittext;
                int duration = Toast.LENGTH_SHORT;

                Intent abrirFormulario = new Intent(MainActivity.this, RespuestaCompra.class);
                abrirFormulario.putExtra("respuesta_venta", stredittext );

                MainActivity.this.startActivity(abrirFormulario);
            }
        }
    }

    public void cargarFormulario(View view) throws IOException, JSONException {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Map<String, Object> informacion_pago = new HashMap<String, Object>();
                    informacion_pago.put("numero_pedido", random.nextInt(99900)+100);
                    informacion_pago.put("correo_electronico", correo.getText().toString());
                    informacion_pago.put("nombres", nombres.getText().toString());
                    informacion_pago.put("apellidos", apellidos.getText().toString());
                    informacion_pago.put("id_usuario_comercio", "0001");
                    informacion_pago.put("moneda", "PEN");
                    informacion_pago.put("monto", monto.getText().toString());
                    informacion_pago.put("descripcion", "Venta de prueba");
                    informacion_pago.put("ciudad", ciudad.getText().toString());
                    informacion_pago.put("pais", pais.getText().toString());
                    informacion_pago.put("direccion", direccion.getText().toString());
                    informacion_pago.put("telefono", telefono.getText().toString());

                    String json = new GsonBuilder().create().toJson(informacion_pago, Map.class);

                    String url = "https://integ-com.culqi.com/movil";

                    if (terminos.isChecked()) {

                        HttpResponse response =  makeRequest(url, json);; // some response object
                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                        String json_response = reader.readLine();
                        //JSONTokener tokener = new JSONTokener(json_response);
                        //JSONArray finalResult = new JSONArray(tokener);

                        RespuestaDemo respuestaDemo = null;
                        try {
                            respuestaDemo = new ObjectMapper().readValue(json_response, RespuestaDemo.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (respuestaDemo.getRespuesta().equals("venta_registrada")) {

                            Intent i = new Intent(MainActivity.this,  CheckoutActivity.class);
                            i.putExtra("codigo_comercio", "testc101");
                            i.putExtra("informacion_venta", respuestaDemo.getInformacion_venta() );
                            startActivityForResult(i, 1);

                        } else {

                            final RespuestaDemo finalRespuestaDemo = respuestaDemo;

                            runOnUiThread(new Runnable() {
                                public void run() {

                                    Context context = getApplicationContext();
                                    CharSequence text = finalRespuestaDemo.getMensaje_respuesta();
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            });



                        }

                    } else {

                        runOnUiThread(new Runnable() {
                            public void run() {

                                Context context = getApplicationContext();
                                CharSequence text = "Por favor acepta los términos y condiciones.";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
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
