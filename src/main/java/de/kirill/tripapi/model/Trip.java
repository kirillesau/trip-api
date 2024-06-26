package de.kirill.tripapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name = "";
    private int discount = 0;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TripType type;
    private String link;
    private String description;
    private String performance;
    private String additionalInfo;
    private String validity;
    private Double price;
    private boolean favorite = false;

    @JsonManagedReference
    @OneToMany(mappedBy = "trip", cascade = ALL, orphanRemoval = true)
    private List<TripBooking> bookings = new ArrayList<>();
}