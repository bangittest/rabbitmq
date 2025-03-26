package com.bn.rabbitmq.repository;

import com.bn.rabbitmq.domain.TripDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDetailRepository extends JpaRepository<TripDetail, Long> {
    TripDetail findByTripIdAndStationId(Long tripId,Long stationId);
}
