package com.taxiboking.project.taxiboiking.strategies;

import com.taxiboking.project.taxiboiking.entities.RideRequest;

public interface RideFareCalculationStrategy {
    static final double RIDE_FARE_MULTIPLER=10;

    double calculateFare(RideRequest rideRequest);
}
