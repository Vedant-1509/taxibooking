package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.dto.RideRequestDto;
import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.RideRequest;
import com.taxiboking.project.taxiboiking.entities.Rider;
import com.taxiboking.project.taxiboiking.entities.enums.RideRequestStatus;
import com.taxiboking.project.taxiboiking.entities.enums.RideStatus;
import com.taxiboking.project.taxiboiking.exceptions.ResourceNotFoundException;
import com.taxiboking.project.taxiboiking.repositories.RideRepository;
import com.taxiboking.project.taxiboiking.services.RideRequestService;
import com.taxiboking.project.taxiboiking.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceimpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(()->new ResourceNotFoundException("Ride not found "+rideId));
    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride= modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.CONFIRM);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
       ride.setRideStatus(rideStatus);
       return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllridesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllridesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);
    }

    private String generateRandomOTP() {
        Random random = new Random();
        int otpInt = random.nextInt(10000); // Generates a number between 0 and 9999
        return String.format("%04d", otpInt); // Formats the number to a 4-digit string
    }

}
