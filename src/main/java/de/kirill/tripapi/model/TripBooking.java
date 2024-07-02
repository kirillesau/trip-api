package de.kirill.tripapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
@Table(name = "trip_booking")
public class TripBooking {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    private Date bookingDate;

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
