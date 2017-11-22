package com.example.shipmenttracking;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anura on 11/21/2017.
 */
@Module
public class WebServiceModule {
    private String mBaseUrl;
    // Constructor needs one parameter to instantiate.
    public WebServiceModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    ShipmentAPI provideShipmentAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .build();
        return retrofit.create(ShipmentAPI.class);
    }
}
