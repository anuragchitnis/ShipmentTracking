package com.example.shipmenttracking.components;

import com.example.shipmenttracking.WebServiceModule;
import com.example.shipmenttracking.repository.ShipmentRepository;

import dagger.Component;

/**
 * Created by anura on 11/21/2017.
 */
@Component(modules = {WebServiceModule.class})
public interface WebServiceComponent {

    void inject(ShipmentRepository shipmentRepository);

}
