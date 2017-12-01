package com.example.shipmenttracking;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.example.shipmenttracking.model.Shipment;
import com.example.shipmenttracking.repository.ShipmentRepository;
import com.example.shipmenttracking.viewmodel.ShipmentViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by anura on 11/30/2017.
 */

public class ShipmentViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    MutableLiveData<Shipment> shipmentLiveData = new MutableLiveData<>();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ShipmentRepository shipmentRepository;

    @Before
    public void setUp() {
        when(shipmentRepository.getShipment("123456789")).thenReturn(shipmentLiveData);
    }

    @Test
    public void testGetShipment() {
        ShipmentViewModel shipmentViewModel = new ShipmentViewModel(shipmentRepository);
        Shipment shipment = new Shipment("123456789", 12456789,123456789);
        Observer<Shipment> observer = mock(Observer.class);
        shipmentViewModel.getShipment("123456789").observeForever(observer);
        verify(observer, never()).onChanged(any(Shipment.class));

        shipmentLiveData.setValue(shipment);
        verify(observer).onChanged(shipment);
        //reset(observer);
    }
}
