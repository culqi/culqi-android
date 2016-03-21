package com.culqi.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import org.json.JSONException;

import java.io.IOException;

public class ProductsActivity extends AppCompatActivity {

    Integer montoTotal;
    String producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        montoTotal = 0;
        producto = "";
    }

    public void cargarCarrito(View view)  {

        Intent i = new Intent(ProductsActivity.this,  CartActivity.class);
        i.putExtra("producto", producto);
        i.putExtra("monto", montoTotal.toString());
        startActivityForResult(i, 1);
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_producto1:
                if (checked)
                    producto = "Producto Prueba 1";
                    montoTotal =  10;
                    break;
            case R.id.radio_producto2:
                if (checked)
                    producto = "Producto Prueba 2";
                    montoTotal =  20;
                    break;
            case R.id.radio_producto3:
                if (checked)
                    producto = "Producto Prueba 3";
                    montoTotal =  30;
                    break;
        }
    }

}
