package com.example.shipmenttracking;

import com.example.shipmenttracking.model.Shipment;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anura on 11/21/2017.
 */

@Singleton
public interface ShipmentAPI {
    /**
     * @GET declares an HTTP GET request
     * @Path("trackingId") annotation on the userId parameter marks it as a
     * replacement for the {trackingId} placeholder in the @GET path
     */
    @GET("v1/shipment/{trackingId}")
    Call<Shipment> getShipment(@Path("trackingId") String trackingId);
}
