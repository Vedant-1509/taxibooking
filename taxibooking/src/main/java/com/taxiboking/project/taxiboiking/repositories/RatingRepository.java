package com.taxiboking.project.taxiboiking.repositories;

import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Rating;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating>findByRider(Rider rider);
    List<Rating>findByDriver(Driver driver);

   Optional<Rating> findByRide(Ride ride);
}
