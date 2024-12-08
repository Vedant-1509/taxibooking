package com.taxiboking.project.taxiboiking.repositories;

import com.taxiboking.project.taxiboiking.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
