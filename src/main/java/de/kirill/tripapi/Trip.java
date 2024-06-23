package de.kirill.tripapi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;
    private Boolean halfPrice = false;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TripType type;
    private String link;
    private String description;
    private String performance;
    private String additionalInfo;
    private Date validDate;
    private Double price;
    private Boolean taken = false;
}
