package org.geobytelogistics.controller;

import lombok.RequiredArgsConstructor;
import org.geobytelogistics.dto.request.LocationRequest;
import org.geobytelogistics.dto.response.ApiResponse;
import org.geobytelogistics.dto.response.LocationResponse;
import org.geobytelogistics.entities.Location;
import org.geobytelogistics.service.LocationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-locations")
@RequiredArgsConstructor
public class LocationController {

        private final LocationServices locationServices;

        @GetMapping("/get -all-locations")
        public ResponseEntity<List<ApiResponse<Location>>> getAllLocations() {
            List<ApiResponse<Location>> locations = locationServices.getAllLocations();
            return ResponseEntity.ok(locations);
        }

        @PostMapping("/add-location")
        public ResponseEntity<ApiResponse<LocationResponse>> addLocation(@RequestBody @Validated LocationRequest request) {
            ApiResponse<LocationResponse> response = locationServices.addLocation(request);
            return ResponseEntity.ok(response);
        }

        @DeleteMapping("/remove-location/{locationId}")
        public ResponseEntity<ApiResponse<LocationResponse>> removeLocation(@PathVariable Long locationId) {
            ApiResponse<LocationResponse> response = locationServices.removeLocation(locationId);
            return ResponseEntity.ok(response);
        }

        @PutMapping("/update-location")
        public ResponseEntity<ApiResponse<LocationResponse>> updateLocation(@RequestParam("locationId") Long locationId,@RequestBody @Validated LocationRequest request) {
            ApiResponse<LocationResponse> response = locationServices.updateLocation(locationId, request);
            return ResponseEntity.ok(response);
        }


}
