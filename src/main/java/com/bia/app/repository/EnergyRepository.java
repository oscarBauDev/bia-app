package com.bia.app.repository;

import com.bia.app.model.Energy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EnergyRepository extends JpaRepository<Energy, Long> {

    Optional<Energy> findFirstByMeterDateBetweenOrderByMeterDateAsc(LocalDateTime startTime, LocalDateTime endTime);
    Optional<Energy> findFirstByMeterDateBetweenOrderByMeterDateDesc(LocalDateTime startTime, LocalDateTime endTime);

}
