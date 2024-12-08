package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.RideDto;
import com.taxiboking.project.taxiboiking.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);
    RideDto cancelRide(Long rideId);


    RideDto startRide(Long rideId,String otp);

    RideDto endRide(Long rideId);

    RideDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);
    Driver getCurrentDriver();

    void updateDriverAvailability(Driver driver, boolean available);

}
