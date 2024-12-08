package com.taxiboking.project.taxiboiking.strategies;

import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
