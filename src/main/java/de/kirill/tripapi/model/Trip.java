package de.kirill.tripapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    private String name;
    private int discount = 0;

    @NonNull
    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TripType type;
    private String link = "";
    private String description = "";
    private String performance = "";
    private String additionalInfo = "";
    private String validity = "";
    private Double price = 0.0;
    private boolean favorite = false;

    @JsonManagedReference
    @OneToMany(mappedBy = "trip", cascade = ALL, orphanRemoval = true)
    private List<TripBooking> bookings = new ArrayList<>();
}