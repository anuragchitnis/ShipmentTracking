package com.example.shipmenttracking.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shipmenttracking.R;
import com.example.shipmenttracking.viewmodel.ShipmentDetailsViewModel;

public class MainActivity extends AppCompatActivity {

    private ShipmentDetailsViewModel viewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(ShipmentDetailsViewModel.class);
        viewModel.init("220088641150");

        textView = (TextView) findViewById(R.id.helloWorld);

        viewModel.getShipment().observe(this, shipment ->
        {
            textView.setText(shipment.toString());
        });
    }
}
