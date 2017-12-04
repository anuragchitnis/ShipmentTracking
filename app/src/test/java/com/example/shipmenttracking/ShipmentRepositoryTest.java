package com.example.shipmenttracking;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;

import com.example.shipmenttracking.model.Shipment;
import com.example.shipmenttracking.repository.ShipmentRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static junit.framework.Assert.assertEquals;

/**
 * Created by anura on 11/22/2017.
 */

public class ShipmentRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private MockWebServer server;
    private ShipmentRepository shipmentRepository;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer() ;
        server.start();
        shipmentRepository = new ShipmentRepository(server.url("/").toString());
    }

    @Test
    public void testGetShipment() throws IOException {
        String fileName = "quote_200_ok_response.json";
        URL resource = this.getClass().getClassLoader().getResource(fileName);
        String body = readFile(new File(resource.getPath()));

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(body));

        LiveData<Shipment> liveData = shipmentRepository.getShipment("220088641155");
        Shipment givenShipment = new Shipment("220088641155", 151135791,150147720);
        Shipment receivedShipment =  liveData.getValue();

        while (receivedShipment != null) {
            assertEquals(givenShipment, receivedShipment);
        }

    }

    String readFile(File fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
