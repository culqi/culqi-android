package com.culqi.checkout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    TextView productoNombre;
    TextView montoVenta;
    String producto;
    String monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        producto = intent.getStringExtra("producto");
        monto = intent.getStringExtra("monto");

        productoNombre   = (TextView)findViewById(R.id.nombreProducto);
        montoVenta   = (TextView)findViewById(R.id.montoVenta);

        montoVenta.setText("S/" + monto);
        productoNombre.setText(producto);

    }

    public void cargarVenta(View view)  {

        Intent i = new Intent(CartActivity.this,  MainActivity.class);
        i.putExtra("producto", producto);
        i.putExtra("monto", monto.toString());
        startActivityForResult(i, 1);
    }

}
