package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.RideDto;
import com.taxiboking.project.taxiboiking.dto.RideRequestDto;
import com.taxiboking.project.taxiboiking.dto.RiderDto;
import com.taxiboking.project.taxiboiking.entities.Rider;
import com.taxiboking.project.taxiboiking.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide(Long rideId);


    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewrider(User user);
    Rider getCurrentRider();
}
