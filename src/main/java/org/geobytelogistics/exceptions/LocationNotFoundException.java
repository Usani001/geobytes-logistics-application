package org.geobytelogistics.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class LocationNotFoundException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public LocationNotFoundException(String message) {
        super(message);

    }

    public LocationNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
