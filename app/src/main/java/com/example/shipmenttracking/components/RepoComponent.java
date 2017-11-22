package com.example.shipmenttracking.components;

import com.example.shipmenttracking.RepoModule;
import com.example.shipmenttracking.viewmodel.ShipmentDetailsViewModel;

import javax.inject.Singleton;

import dagger.Component;

;

/**
 * Created by anura on 11/21/2017.
 */

@Singleton
@Component(modules={RepoModule.class})
public interface RepoComponent {
    void inject(ShipmentDetailsViewModel shipmentDetailsViewModel);
}
