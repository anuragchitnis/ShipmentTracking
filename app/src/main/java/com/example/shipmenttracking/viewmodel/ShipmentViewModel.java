package com.example.shipmenttracking.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.shipmenttracking.RepoModule;
import com.example.shipmenttracking.components.DaggerRepoComponent;
import com.example.shipmenttracking.components.RepoComponent;
import com.example.shipmenttracking.model.Shipment;
import com.example.shipmenttracking.repository.ShipmentRepository;

import javax.inject.Inject;

/**
 * Created by anura on 11/21/2017.
 */

public class ShipmentViewModel extends ViewModel {
    private LiveData<Shipment> shipment;
    @Inject
    public ShipmentRepository shipmentRepo;

    public ShipmentViewModel() {
        RepoComponent repoComponent = DaggerRepoComponent.builder()
                .repoModule(new RepoModule())
                .build();
        repoComponent.inject(this);
    }

    @Inject
    public ShipmentViewModel(ShipmentRepository shipmentRepo) {
        this();
        this.shipmentRepo = shipmentRepo;
    }

    public LiveData<Shipment> getShipment(String trackingId) {
        if(shipment != null) {
            return this.shipment;
        } else {
//            Log.e("ViewModel","New Shipment object created");
            shipment = shipmentRepo.getShipment(trackingId);
            return shipment;
        }
    }
}
