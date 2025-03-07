package com.taxiboking.project.taxiboiking.controllers;

import com.taxiboking.project.taxiboiking.dto.*;
import com.taxiboking.project.taxiboiking.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/rider")
@RequiredArgsConstructor // can define constructor dependencies without the constructor

public class RiderController {


    private final RiderService riderService;
    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(),ratingDto.getRating()));
    }

    @PostMapping("/getMyprofile")
    public ResponseEntity<RiderDto> getMyprofile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffset,pageSize, Sort.by(Sort.Direction.DESC,"createdTime","id"));
        return ResponseEntity.ok((Page<RideDto>) riderService.getAllMyRides(pageRequest));


    }
    @PostMapping("/rateDriver/{driverId}/{rating}")
    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long driverId,@PathVariable Integer rating){
        return ResponseEntity.ok(riderService.rateDriver(driverId,rating));
    }


}
