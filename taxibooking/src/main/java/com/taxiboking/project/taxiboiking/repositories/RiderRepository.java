package com.taxiboking.project.taxiboiking.repositories;

import com.taxiboking.project.taxiboiking.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}
