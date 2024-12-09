package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.dto.RideRequestDto;
import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.RideRequest;
import com.taxiboking.project.taxiboiking.entities.Rider;
import com.taxiboking.project.taxiboiking.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);



    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride , RideStatus rideStatus);

    Page<Ride> getAllridesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllridesOfDriver(Driver driver, PageRequest pageRequest);



}
