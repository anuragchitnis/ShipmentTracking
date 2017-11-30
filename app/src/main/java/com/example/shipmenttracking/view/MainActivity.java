package com.example.shipmenttracking.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shipmenttracking.R;
import com.example.shipmenttracking.viewmodel.ShipmentDetailsViewModel;

public class MainActivity extends AppCompatActivity {

    private ShipmentDetailsViewModel viewModel;
    private TextView trackingNumberText;
    private TextView timestampText;
    private TextView shipmentCreateDateText;
    private EditText trackingIdEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(ShipmentDetailsViewModel.class);

        trackingNumberText = (TextView) findViewById(R.id.trackingNumber);
        timestampText = (TextView) findViewById(R.id.timestamp);
        shipmentCreateDateText = (TextView) findViewById(R.id.shipmentCreateDate);
        trackingIdEditText = (EditText) findViewById(R.id.trackingIdEditText);
        submitButton = (Button) findViewById(R.id.submitButton);

        trackingIdEditText.setText("220088641150");

        submitButton.setOnClickListener((view) -> {
            //"220088641150"
            viewModel.init(trackingIdEditText.getText().toString());
            viewModel.getShipment().observe(this, shipment ->
            {
                trackingNumberText.setText(shipment.getTrackingNumber());
                timestampText.setText(String.valueOf(shipment.getTimestamp()));
                shipmentCreateDateText.setText(String.valueOf(shipment.getShipmentCreateDate()));
            });
        });


    }
}
