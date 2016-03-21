package com.culqi.checkout;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by wmuro on 25/01/16.
 */
public class WebJavaScriptInterface {

    Context mContext;

    WebJavaScriptInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void mostrarRespuesta(String respuesta_formulario) {

        Intent intent = new Intent();
        intent.setAction(MainActivity.CERRAR_FORMULARIO);
        intent.putExtra("RESPUESTA", respuesta_formulario);

        mContext.sendBroadcast(intent);
    }
}