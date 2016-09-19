package com.culqi.checkout;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Token {

    public static HttpResponse makeRequest(String uri, String json, String apiKey) {
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + apiKey);
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

    public String getToken(Card card, final String merchantCode) {

        String responseToken;

        try {

            Map<String, Object> informacion_pago = new HashMap<String, Object>();
            informacion_pago.put("correo_electronico", card.getCorreo_electronico());
            informacion_pago.put("nombre", card.getNombre());
            informacion_pago.put("apellido", card.getApellido());
            informacion_pago.put("numero", card.getNumero());
            informacion_pago.put("cvv", card.getCvv());
            informacion_pago.put("a_exp", card.getA_exp());
            informacion_pago.put("m_exp", card.getM_exp());

            String json = new GsonBuilder().create().toJson(informacion_pago, Map.class);

            String url = "https://integ-pago.culqi.com/api/v1/tokens";

            HttpResponse response = makeRequest(url, json, merchantCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

            String json_response = reader.readLine();

            TokenResponse tokenResponse = null;


            try {

                tokenResponse = new ObjectMapper().readValue(json_response, TokenResponse.class);

                if (tokenResponse.getObjeto().equals("token")) {

                    responseToken = tokenResponse.getId();

                    return responseToken;


                } else {

                    responseToken = "error";

                    return responseToken;

                }

            } catch (IOException e) {

                return "error";

            }


        } catch (Exception e) {

            return "error";

        }

    }

}
