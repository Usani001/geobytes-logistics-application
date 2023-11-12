package org.geobytelogistics.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class LocationRequest {

    @NotBlank(message = "Please provide a location")
    private String locationName;


    private double latitude;


    private double longitude;
}
