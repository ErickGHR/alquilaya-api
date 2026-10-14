package com.api.alquilaya.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.alquilaya.models.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("""
            select count(r) > 0 from Rental r
            where r.vehicle.id = :vehicleId
              and r.status <> com.api.alquilaya.models.Rental.RentalStatus.COMPLETED
              and (:rentalId is null or r.id <> :rentalId)
              and r.rentalDate <= :returnDate
              and r.returnDate >= :rentalDate
            """)
    boolean hasOverlap(
            @Param("vehicleId") Long vehicleId,
            @Param("rentalId") Long rentalId,
            @Param("rentalDate") LocalDate rentalDate,
            @Param("returnDate") LocalDate returnDate);
}
