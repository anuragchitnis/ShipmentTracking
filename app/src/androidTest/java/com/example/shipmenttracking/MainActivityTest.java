package com.example.shipmenttracking;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.shipmenttracking.model.Shipment;
import com.example.shipmenttracking.view.MainActivity;
import com.example.shipmenttracking.viewmodel.ShipmentViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anura on 11/30/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    private ShipmentViewModel viewModel;
    private MutableLiveData<Shipment> shipmentLiveData = new MutableLiveData<>();

    @Before
    public void setUp() {
        viewModel = mock(ShipmentViewModel.class);
        when(viewModel.getShipment("123456789")).thenReturn(shipmentLiveData);
        //when(viewModel.getRepositories()).thenReturn(repoListData);
    }

//    @Test
//    public void testShipmentDetails() {
//    }

    @Test
    public void loading() {
        Shipment shipment = new Shipment("123456789", 12456789,123456789);
        activityRule.getActivity().runOnUiThread(()-> {
            shipmentLiveData.setValue(shipment);
        });


        onView(withId(R.id.submitButton))
                .perform(click());
        onView(withId(R.id.trackingNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.timestamp)).check(matches(isDisplayed()));
        onView(withId(R.id.shipmentCreateDate)).check(matches(isDisplayed()));
        //onView(withId(R.id.retry)).check(matches(not(isDisplayed())));
    }
//
//    @Test
//    public void error() throws InterruptedException {
////        doNothing().when(viewModel).retry();
////        userData.postValue(Resource.error("wtf", null));
////        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
////        onView(withId(R.id.error_msg)).check(matches(withText("wtf")));
////        onView(withId(R.id.retry)).check(matches(isDisplayed()));
////        onView(withId(R.id.retry)).perform(click());
////        verify(viewModel).retry();
//    }
}
