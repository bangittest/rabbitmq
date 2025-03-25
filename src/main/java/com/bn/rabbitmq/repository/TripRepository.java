package com.bn.rabbitmq.repository;


import com.bn.rabbitmq.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
