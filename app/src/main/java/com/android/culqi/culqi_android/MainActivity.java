package com.android.culqi.culqi_android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.culqi.culqi_android.Culqi.Card;
import com.android.culqi.culqi_android.Culqi.Token;
import com.android.culqi.culqi_android.Culqi.TokenCallback;
import com.android.culqi.culqi_android.Validation.Validation;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Token token;

    Validation validation;

    ProgressDialog progress;

    TextView txtcardnumber, txtcvv, txtmonth, txtyear, txtemail, kind_card, result;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validation = new Validation();

        progress = new ProgressDialog(this);
        progress.setMessage("Validando informacion de la tarjeta");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        txtcardnumber = (TextView) findViewById(R.id.txt_cardnumber);

        txtcvv = (TextView) findViewById(R.id.txt_cvv);

        txtmonth = (TextView) findViewById(R.id.txt_month);

        txtyear = (TextView) findViewById(R.id.txt_year);

        txtemail = (TextView) findViewById(R.id.txt_email);

        kind_card = (TextView) findViewById(R.id.kind_card);

        result = (TextView) findViewById(R.id.token_id);

        btnPay = (Button) findViewById(R.id.btn_pay);

        txtcvv.setEnabled(false);

        txtcardnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    txtcvv.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = txtcardnumber.getText().toString();
                if(s.length() == 0) {
                    txtcardnumber.setBackgroundResource(R.drawable.border_error);
                }

                if(validation.luhn(text)) {
                    txtcardnumber.setBackgroundResource(R.drawable.border_sucess);
                } else {
                    txtcardnumber.setBackgroundResource(R.drawable.border_error);
                }

                int cvv = validation.bin(text, kind_card);
                if(cvv > 0) {
                    txtcvv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(cvv)});
                    txtcvv.setEnabled(true);
                } else {
                    txtcvv.setEnabled(false);
                    txtcvv.setText("");
                }
            }
        });

        txtyear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = txtyear.getText().toString();
                if(validation.year(text)){
                    txtyear.setBackgroundResource(R.drawable.border_error);
                } else {
                    txtyear.setBackgroundResource(R.drawable.border_sucess);
                }
            }
        });

        txtmonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = txtmonth.getText().toString();
                if(validation.month(text)){
                    txtmonth.setBackgroundResource(R.drawable.border_error);
                } else {
                    txtmonth.setBackgroundResource(R.drawable.border_sucess);
                }
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            progress.show();

            Card card = new Card(txtcardnumber.getText().toString(), txtcvv.getText().toString(), 9, 2020, txtemail.getText().toString());

            Token token = new Token("pk_test_vzMuTHoueOMlgUPj");

            token.createToken(getApplicationContext(), card, new TokenCallback() {
                @Override
                public void onSuccess(JSONObject token) {
                    try {
                        result.setText(token.get("id").toString());
                    } catch (Exception ex){
                        progress.hide();
                    }
                    progress.hide();
                }

                @Override
                public void onError(Exception error) {
                    progress.hide();
                }
            });

            }
        });

    }

}
