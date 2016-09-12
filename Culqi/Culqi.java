package com.culqi.checkout;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Culqi {

    public String merchantCode;
    
    public static HttpResponse makeRequest(String uri, String json, String merchantCode) {
       try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + auth);
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


    public void createToken() throws IOException, JSONException {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {

                    Integer montoTotalVenta = Integer.parseInt(montoVenta)*100;

                    pedidoNum = random.nextInt(99900)+100;

                    Map<String, Object> informacion_pago = new HashMap<String, Object>();
                    informacion_pago.put("numero_pedido", pedidoNum );
                    informacion_pago.put("correo_electronico", correo.getText().toString());
                    informacion_pago.put("nombres", nombres.getText().toString());
                    informacion_pago.put("apellidos", apellidos.getText().toString());
                    informacion_pago.put("id_usuario_comercio", "0001");
                    informacion_pago.put("moneda", "PEN");
                    informacion_pago.put("monto", montoTotalVenta.toString());
                    informacion_pago.put("descripcion", producto);
                    informacion_pago.put("ciudad", ciudad.getText().toString());
                    informacion_pago.put("pais", pais.getText().toString());
                    informacion_pago.put("direccion", direccion.getText().toString());
                    informacion_pago.put("telefono", telefono.getText().toString());

                    String json = new GsonBuilder().create().toJson(informacion_pago, Map.class);

                    String url = "https://integ-com.culqi.com/venta/movil";

                    if (terminos.isChecked()) {

                        HttpResponse response =  makeRequest(url, json);; // some response object
                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                        String json_response = reader.readLine();
                        //JSONTokener tokener = new JSONTokener(json_response);
                        //JSONArray finalResult = newamor como  JSONArray(tokener);

                        RespuestaDemo respuestaDemo = null;
                        try {
                            respuestaDemo = new ObjectMapper().readValue(json_response, RespuestaDemo.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (respuestaDemo.getRespuesta().equals("venta_registrada")) {

                            /*Intent i = new Intent(MainActivity.this,  CheckoutActivity.class);
                            i.putExtra("codigo_comercio", "demo");
                            i.putExtra("informacion_venta", respuestaDemo.getInformacion_venta() );
                            startActivityForResult(i, 1);*/

                            final RespuestaDemo finalRespuestaDemo1 = respuestaDemo;
                            runOnUiThread(new Runnable() {
                                public void run() {


                                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                    alert.setTitle("");

                                    webViewDialog = new Dialog(MainActivity.this);
                                    webViewDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    //Inflate the contents of this dialog with the Views defined at 'webviewdialog.xml'
                                    webViewDialog.setContentView(R.layout.webview);
                                    //With this line, the dialog can be dismissed by pressing the back key
                                    webViewDialog.setCancelable(false);
                                    webViewDialog.setCanceledOnTouchOutside(false);
                                    //Initialize the Button object with the data from the 'webviewdialog.xml' file

                                    WebView wv = (WebView) webViewDialog.findViewById(R.id.wb_webview);
                                    wv.loadUrl("https://integ-pago.culqi.com/api/v1/formulario/movil/2/" + "5foGNkdG04MR" + "/" + finalRespuestaDemo1.getInformacion_venta());
                                    WebSettings webSettings = wv.getSettings();
                                    webSettings.setJavaScriptEnabled(true);
                                    webSettings.setBuiltInZoomControls(true);
                                    wv.clearCache(true);
                                    wv.addJavascriptInterface(new WebJavaScriptInterface(MainActivity.this), "CLQMessage");
                                   /* wv.requestFocus(View.FOCUS_DOWN);
                                    wv.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            switch (event.getAction()) {
                                                case MotionEvent.ACTION_DOWN:
                                                case MotionEvent.ACTION_UP:
                                                    if (!v.hasFocus()) {
                                                        v.requestFocus();
                                                    }
                                                    break;
                                            }
                                            return false;
                                        }
                                    });
                                    alert.setView(wv);
                                    alert.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                        }
                                    });*/

                                    //alertDialog = alert.create();

                                    //alertDialog.show();
                                    WindowManager.LayoutParams wmlp = webViewDialog.getWindow().getAttributes();

                                    wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                                    //wmlp.x = 100;   //x position
                                    wmlp.y = 0;   //y position

                                    webViewDialog.show();

                                }
                            });

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

    public void responseToParent(String respuesta) {

        Intent intent = new Intent();
        intent.putExtra("respuesta_venta",respuesta);
        setResult(RESULT_OK, intent);
        finish();
    }
}
