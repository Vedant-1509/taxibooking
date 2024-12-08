package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMimpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public double calculateDistance(Point src, Point dest) {
        //call 3rd party api OSRM
        String uri =src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
        try{
            OSRMResponseDto responseDto= RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            return responseDto.getRoutes().get(0).getDistance() /1000.0;

        }catch (Exception e){
            throw new RuntimeException("Error in getting Data from OSRM "+e.getMessage());
        }

    }
}
@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}
@Data
class OSRMRoute{
    private Double distance;
}
