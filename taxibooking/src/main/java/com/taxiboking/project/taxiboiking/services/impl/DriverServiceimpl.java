package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.RideDto;
import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.RideRequest;
import com.taxiboking.project.taxiboiking.entities.enums.RideRequestStatus;
import com.taxiboking.project.taxiboiking.entities.enums.RideStatus;
import com.taxiboking.project.taxiboiking.exceptions.ResourceNotFoundException;
import com.taxiboking.project.taxiboiking.repositories.DriverRepository;
import com.taxiboking.project.taxiboiking.services.DriverService;
import com.taxiboking.project.taxiboiking.services.RideRequestService;
import com.taxiboking.project.taxiboiking.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverServiceimpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest=rideRequestService.findRideRequestById(rideRequestId);

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride Request cannot be accepted , status is " +rideRequest.getRideRequestStatus());
        }

        Driver currentDriver=getCurrentDriver();

        if (!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        Driver savedDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest,savedDriver);


        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start the ride as he ha not accepted earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRM)){
            throw new RuntimeException("Ride cannot be cancelled invalid status "+ride.getRideStatus());
        }

        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateDriverAvailability(driver,true);

        return modelMapper.map(ride,RideDto.class);

    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start the ride as he ha not accepted earlier");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRM)){
            throw new RuntimeException("ride status is not confirmed hence cannot be started status "+ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp is not valid");
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);
        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver,DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();

        return rideService.getAllridesOfDriver(currentDriver.getId(),pageRequest).map(ride -> modelMapper.map(ride,RideDto.class)) ;
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()->new ResourceNotFoundException("DRiver not found with id 2"));
    }

    @Override
    public void updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        driverRepository.save(driver);
    }
}
