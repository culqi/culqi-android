package com.culqi.checkout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;

/**
 * Created by wmuro on 22/01/16.
 */
public class CheckoutActivity extends AppCompatActivity {


    public String codigoComercio;

    public String informacionVenta;


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //UI Updates
            String respuesta = intent.getStringExtra("RESPUESTA");

            responseToParent(respuesta);

            //CheckoutActivity.this.finish();
        }

    };

    @Override
    public void onResume() {
        super.onResume();

        //UI Updates

      //  IntentFilter filter = new IntentFilter();
       // filter.addAction(CERRAR_FORMULARIO);
       // this.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();

        this.unregisterReceiver(broadcastReceiver);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        codigoComercio = intent.getStringExtra("codigo_comercio");
        informacionVenta = intent.getStringExtra("informacion_venta");

        WebView browser = (WebView) findViewById(R.id.webView);
        browser.loadUrl("https://integ-pago.culqi.com/api/v1/formulario/movil/2/" + codigoComercio + "/" + informacionVenta);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        //browser.setWebContentsDebuggingEnabled(true);
        browser.addJavascriptInterface(new WebJavaScriptInterface(this), "CLQMessage");

    }

    public void responseToParent(String respuesta) {

        Intent intent = new Intent();
        intent.putExtra("respuesta_venta",respuesta);
        setResult(RESULT_OK, intent);
        finish();
    }


}

