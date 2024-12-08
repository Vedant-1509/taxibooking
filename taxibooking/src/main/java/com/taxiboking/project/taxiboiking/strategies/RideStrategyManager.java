package com.taxiboking.project.taxiboiking.strategies;

import com.taxiboking.project.taxiboiking.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.taxiboking.project.taxiboiking.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.taxiboking.project.taxiboiking.strategies.impl.RideFareDdefaultFarecalculationStrategy;
import com.taxiboking.project.taxiboiking.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RideFareDdefaultFarecalculationStrategy defaultFareCalculationStrategy;


    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if (riderRating>=4.8){
            return driverMatchingHighestRatedDriverStrategy;
        }else {
            return nearestDriverStrategy;
        }

    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime=LocalTime.of(18,00);
        LocalTime surgeEndTime = LocalTime.of(21,00);
        LocalTime currentTime =LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime)&&currentTime.isBefore(surgeEndTime);

        if (isSurgeTime){
            return surgePricingFareCalculationStrategy;
        }else {
            return defaultFareCalculationStrategy;
        }
    }

}
