package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.entities.RideRequest;
import com.taxiboking.project.taxiboiking.exceptions.ResourceNotFoundException;
import com.taxiboking.project.taxiboiking.repositories.RideRequestRepository;
import com.taxiboking.project.taxiboiking.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).
                orElseThrow(()->new ResourceNotFoundException("RideRequest not found with id "+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()->new ResourceNotFoundException("Ride request not found with id "+rideRequest.getId()));

        rideRequestRepository.save(rideRequest);
    }
}
