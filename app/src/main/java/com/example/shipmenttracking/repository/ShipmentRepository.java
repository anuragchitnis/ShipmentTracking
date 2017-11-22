package com.example.shipmenttracking.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.shipmenttracking.ShipmentAPI;
import com.example.shipmenttracking.WebServiceModule;
import com.example.shipmenttracking.components.DaggerWebServiceComponent;
import com.example.shipmenttracking.components.WebServiceComponent;
import com.example.shipmenttracking.model.Shipment;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anura on 11/21/2017.
 */

@Singleton
public class ShipmentRepository {

    private WebServiceComponent webServiceComponent;

    @Inject
    public ShipmentAPI shipmentAPI;
    // simple in memory cache, details omitted for brevity
    private HashMap<String, MutableLiveData> userCache = new HashMap<>();

    public ShipmentRepository(String baseUrl) {
        webServiceComponent = DaggerWebServiceComponent.builder()
                .webServiceModule(new WebServiceModule(baseUrl))
                .build();
        webServiceComponent.inject(this);
    }

    public LiveData<Shipment> getShipment(String trackingId) {
        LiveData<Shipment> cached = userCache.get(trackingId);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<Shipment> data = new MutableLiveData<>();
        userCache.put(trackingId, data);
        // this is still suboptimal but better than before.
        // a complete implementation must also handle the error cases.
        shipmentAPI.getShipment(trackingId).enqueue(new Callback<Shipment>() {
            @Override
            public void onResponse(Call<Shipment> call, Response<Shipment> response) {
                data.setValue(response.body());
                Log.d("ShipmentRepository", response.body().toString());
            }

            @Override
            public void onFailure(Call<Shipment> call, Throwable t) {
                Log.e("ShipmentRepository","Failure in retrofit calling");
            }
        });
        return data;
    }
}
