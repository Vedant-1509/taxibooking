package com.taxiboking.project.taxiboiking.repositories;

import com.taxiboking.project.taxiboiking.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {
}
