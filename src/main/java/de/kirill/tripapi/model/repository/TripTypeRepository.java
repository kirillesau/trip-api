package de.kirill.tripapi.model.repository;

import de.kirill.tripapi.model.TripType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripTypeRepository extends JpaRepository<TripType, Long> {
}
