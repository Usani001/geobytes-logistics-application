package org.geobytelogistics.service;

import org.geobytelogistics.dto.request.LocationRequest;
import org.geobytelogistics.dto.response.ApiResponse;
import org.geobytelogistics.dto.response.LocationResponse;
import org.geobytelogistics.entities.Location;

import java.util.List;

public interface LocationServices {
    List<ApiResponse<Location>> getAllLocations();
    ApiResponse<LocationResponse>  addLocation(LocationRequest request);
    ApiResponse<LocationResponse> removeLocation(Long locationId);
    ApiResponse<LocationResponse> updateLocation(Long locationId, LocationRequest request);

}
