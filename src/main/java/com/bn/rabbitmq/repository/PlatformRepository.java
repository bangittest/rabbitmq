package com.bn.rabbitmq.repository;

import com.bn.rabbitmq.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

}
