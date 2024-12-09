package com.taxiboking.project.taxiboiking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RatingDto {
    private Long rideId;
    private Integer rating;

}
