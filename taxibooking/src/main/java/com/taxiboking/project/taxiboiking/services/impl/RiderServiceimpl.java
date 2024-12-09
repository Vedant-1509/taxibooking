package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.RideDto;
import com.taxiboking.project.taxiboiking.dto.RideRequestDto;
import com.taxiboking.project.taxiboiking.dto.RiderDto;
import com.taxiboking.project.taxiboiking.entities.*;
import com.taxiboking.project.taxiboiking.entities.enums.RideRequestStatus;
import com.taxiboking.project.taxiboiking.entities.enums.RideStatus;
import com.taxiboking.project.taxiboiking.exceptions.ResourceNotFoundException;
import com.taxiboking.project.taxiboiking.repositories.RideRequestRepository;
import com.taxiboking.project.taxiboiking.repositories.RiderRepository;
import com.taxiboking.project.taxiboiking.services.DriverService;
import com.taxiboking.project.taxiboiking.services.RatingService;
import com.taxiboking.project.taxiboiking.services.RideService;
import com.taxiboking.project.taxiboiking.services.RiderService;
import com.taxiboking.project.taxiboiking.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j

public class RiderServiceimpl implements RiderService {


    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider = getCurrentRider();
        RideRequest rideRequest=modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);


        //calculating the fare
        Double fare=rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest=rideRequestRepository.save(rideRequest);

        List <Driver> drivers=rideStrategyManager
                .driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

//        TODO :Send notification to all the drivers about ride request



        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException(("Rider dont own this ride"));
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRM)){
            throw new RuntimeException("Ride cannot be cancelled invalid status "+ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);

        return modelMapper.map(savedRide,RideDto.class);


    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider =getCurrentRider();

        if (!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not the owner of this ride");
        }

        if (!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("ride status is not ongoing hence cannot be started status "+ride.getRideStatus());
        }
        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider,RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllridesOfRider(currentRider,pageRequest)
                .map(ride -> modelMapper.map(ride,RideDto.class));

    }

    @Override
    public Rider createNewrider(User user) {
        Rider rider = Rider
                .builder()
                .user(user).
                rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L)
                .orElseThrow(()->new ResourceNotFoundException("rider not foungd with id 1 "));

        //to implement spring security
    }


}
