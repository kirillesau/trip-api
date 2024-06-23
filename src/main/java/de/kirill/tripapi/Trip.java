package de.kirill.tripapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private String type;
    private String link;
    private String description;
    private String performance;
    private String additionalInfo;
    private Date validDate;
    private Double price;
    private Boolean taken = false;
}
