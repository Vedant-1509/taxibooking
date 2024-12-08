package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
