package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.RiderDto;
import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.Rider;

public interface RatingService {
    DriverDto rateDriver(Ride ride , Integer rating);

    RiderDto rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}
