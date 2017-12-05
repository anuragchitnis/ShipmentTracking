package com.example.shipmenttracking.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.shipmenttracking.R;
import com.example.shipmenttracking.databinding.ActivityMainBinding;
import com.example.shipmenttracking.viewmodel.ShipmentViewModel;

public class MainActivity extends AppCompatActivity {

    private ShipmentViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ShipmentViewModel.class);
    }

    public void onSubmitClicked(View view) {
        viewModel.getShipment(binding.trackingIdEditText.getText().toString()).observe(this, shipment ->
        {
            binding.setShipment(shipment);
        });
    }
}
