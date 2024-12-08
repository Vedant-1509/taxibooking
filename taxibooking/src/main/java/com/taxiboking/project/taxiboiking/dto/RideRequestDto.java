package com.taxiboking.project.taxiboiking.dto;

import com.taxiboking.project.taxiboiking.entities.Rider;
import com.taxiboking.project.taxiboiking.entities.enums.PaymentMethod;
import com.taxiboking.project.taxiboiking.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;

    private LocalDateTime requestTime;

    private Rider rider;


    private RideRequestStatus rideRequestStatus;
    private Double fare;

}
