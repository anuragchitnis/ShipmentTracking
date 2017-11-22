package com.example.shipmenttracking;

import com.example.shipmenttracking.repository.ShipmentRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anura on 11/21/2017.
 */

@Module
public class RepoModule {

    public static final String BASE_URL = "";

    @Provides
    @Singleton
    public ShipmentRepository provideShipmentRepository() {
        return new ShipmentRepository(BASE_URL);
    }
}
