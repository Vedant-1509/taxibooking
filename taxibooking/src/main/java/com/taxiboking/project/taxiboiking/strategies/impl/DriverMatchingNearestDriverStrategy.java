package com.taxiboking.project.taxiboiking.strategies.impl;

import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.RideRequest;
import com.taxiboking.project.taxiboiking.repositories.DriverRepository;
import com.taxiboking.project.taxiboiking.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
