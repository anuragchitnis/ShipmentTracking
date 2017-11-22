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

public class ShipmentDetailsViewModel extends ViewModel {
    private LiveData<Shipment> shipment;
    private RepoComponent repoComponent;

    @Inject
    public ShipmentRepository shipmentRepo;

    public ShipmentDetailsViewModel() {
        repoComponent = DaggerRepoComponent.builder()
                .repoModule(new RepoModule())
                .build();
        repoComponent.inject(this);
    }

    public void init(String trackingId) {
        if (this.shipment != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        shipment = shipmentRepo.getShipment(trackingId);
    }

    public LiveData<Shipment> getShipment() {
        return this.shipment;
    }
}
