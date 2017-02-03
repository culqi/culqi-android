package com.android.culqi.culqi_android.Culqi;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/19/17.
 */

public class Token {

    Config config = new Config();

    private static final String URL = "/tokens/";

    public void createToken(Context context, final ProgressDialog progress, final String cod_commerce, final TextView result, String card_number,
                            String currency_code, String cvv, int expiration_month, int expiration_year,
                            String last_name, String email, String first_name) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject jsonBody = new JSONObject();
        try {
            // change for the las version (work in progress)
            jsonBody = new JSONObject();
            jsonBody.put("card_number", card_number);
            jsonBody.put("cvv", cvv);
            jsonBody.put("expiration_month", expiration_month);
            jsonBody.put("expiration_year", expiration_year);
            jsonBody.put("email", email);
            jsonBody.put("fingerprint","dsf234dfeswdes");
        } catch (Exception ex){
            Log.v("", "ERROR: "+ex.getMessage());
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, config.url_base+URL,jsonBody, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progress.hide();
                    result.setText(response.get("id").toString());
                } catch (Exception ex){
                    progress.hide();
                    Log.v("", response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                Log.v("", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Bearer " + cod_commerce);
                return headers;
            }

        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);

    }

}
