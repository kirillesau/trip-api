package de.kirill.tripapi.web.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND)
public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(long id) {
        super("Trip not found with id: " + id);
    }
}
