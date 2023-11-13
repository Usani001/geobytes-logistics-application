package org.geobytelogistics.service.service_implementation;

import lombok.RequiredArgsConstructor;
import org.geobytelogistics.dto.request.LocationRequest;
import org.geobytelogistics.dto.response.ApiResponse;
import org.geobytelogistics.dto.response.LocationResponse;
import org.geobytelogistics.entities.Location;
import org.geobytelogistics.repository.LocationRepository;
import org.geobytelogistics.service.LocationServices;
import org.geobytelogistics.utils.EntityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationServices {
    private final LocationRepository locationRepository;
    private final EntityMapper entityMapper;

    // View a list of delivery locations
    public List<ApiResponse<Location>> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<ApiResponse<Location>> responseList = new ArrayList<>();

        if (locations.isEmpty()) {
            return responseList;
        }

        for (Location location : locations) {
            ApiResponse<Location> response = new ApiResponse<>("00","",location, "Locations retrieved successfully");
            responseList.add(response);
        }

        return responseList;
    }


    // Add Delivery Locations
    public ApiResponse<LocationResponse>  addLocation(LocationRequest request) {
        Location location = Location.builder()
                .locationName(request.getLocationName())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
        locationRepository.save(location);

        return new ApiResponse<>("00","Delivery location has been added successfully",entityMapper.locationResponse(),"Success");
    }


    // Remove Delivery Location
    public ApiResponse<LocationResponse> removeLocation(LocationRequest request){
        Optional<Location> optionalLocation = locationRepository.findById(request.getId());
        if (optionalLocation.isPresent()) {
            locationRepository.deleteById(request.getId());
            return new ApiResponse<>("00","Delivery location with delivery ID {} "
                    +request.getId()+" has successfully been deleted",null,"Success");
        }
        return new ApiResponse<>("01","Delivery location with delivery ID {} "
                +request.getId()+" cannot be found",null,"Failed");



    }

    // Update Delivery Location
    public ApiResponse<LocationResponse> updateLocation(LocationRequest request){
        Optional<Location> optionalLocation = locationRepository.findById(request.getId());
        if (optionalLocation.isPresent()) {
            Location location = Location.builder()
                    .locationName(request.getLocationName())
                    .longitude(request.getLongitude())
                    .latitude(request.getLatitude())
                    .build();
            locationRepository.save(location);
            return new ApiResponse<>("00","Delivery location with delivery ID {} "
                    +request.getId()+" has successfully been updated",entityMapper.locationResponse(),"Success");
        }
        return new ApiResponse<>("01","Delivery location with delivery ID {} "
                +request.getId()+" cannot be found",null,"Failed");
    }

//    }
//
//    public List<DeliveryLocation> calculateOptimalRoute(Long originId, Long destinationId) {
//        DeliveryLocation origin = locationRepository.findById(originId)
//                .orElseThrow(() -> new EntityNotFoundException("Origin location not found"));
//
//        DeliveryLocation destination = locationRepository.findById(destinationId)
//                .orElseThrow(() -> new EntityNotFoundException("Destination location not found"));
//
//        List<DeliveryLocation> allLocations = locationRepository.findAll();
//        Map<DeliveryLocation, Double> distanceMap = new HashMap<>();
//        Map<DeliveryLocation, DeliveryLocation> predecessorMap = new HashMap<>();
//        Set<DeliveryLocation> unvisitedLocations = new HashSet<>(allLocations);
//
//        // Initialize distanceMap with initial distances
//        for (DeliveryLocation location : allLocations) {
//            distanceMap.put(location, Double.MAX_VALUE);
//        }
//        distanceMap.put(origin, 0.0);
//
//        while (!unvisitedLocations.isEmpty()) {
//            DeliveryLocation currentLocation = findLocationWithMinDistance(unvisitedLocations, distanceMap);
//            unvisitedLocations.remove(currentLocation);
//
//            for (DeliveryLocation neighbor : getNeighbors(currentLocation, allLocations)) {
//                double potentialDistance = distanceMap.get(currentLocation) + calculateDistance(currentLocation, neighbor);
//
//                if (potentialDistance < distanceMap.get(neighbor)) {
//                    distanceMap.put(neighbor, potentialDistance);
//                    predecessorMap.put(neighbor, currentLocation);
//                }
//            }
//        }
//
//        return reconstructOptimalRoute(origin, destination, predecessorMap);
//    }
//
//    private DeliveryLocation findLocationWithMinDistance(Set<DeliveryLocation> locations, Map<DeliveryLocation, Double> distanceMap) {
//        return locations.stream()
//                .min(Comparator.comparingDouble(distanceMap::get))
//                .orElseThrow(() -> new RuntimeException("No location found with minimum distance"));
//    }
//
//    private List<DeliveryLocation> getNeighbors(DeliveryLocation location, List<DeliveryLocation> allLocations) {
//        // In a real-world scenario, you might retrieve neighbors based on geographical proximity
//        // For simplicity, let's consider all locations as neighbors
//        return allLocations;
//    }
//
//    private double calculateDistance(DeliveryLocation location1, DeliveryLocation location2) {
//        // In a real-world scenario, you might use a mapping service or a more accurate formula
//        // For simplicity, let's consider a simple Euclidean distance
//        return Math.sqrt(Math.pow(location1.getLatitude() - location2.getLatitude(), 2)
//                + Math.pow(location1.getLongitude() - location2.getLongitude(), 2));
//    }
//
//    private List<DeliveryLocation> reconstructOptimalRoute(DeliveryLocation origin, DeliveryLocation destination, Map<DeliveryLocation, DeliveryLocation> predecessorMap) {
//        List<DeliveryLocation> optimalRoute = new ArrayList<>();
//        DeliveryLocation currentLocation = destination;
//
//        while (currentLocation != null) {
//            optimalRoute.add(currentLocation);
//            currentLocation = predecessorMap.get(currentLocation);
//        }
//
//        Collections.reverse(optimalRoute);
//
//        return optimalRoute;
//    }
}
