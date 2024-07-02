package de.kirill.tripapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Table(name = "trip_type")
public class TripType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    private String name;
    private String color;
}
