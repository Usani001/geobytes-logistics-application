package org.geobytelogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    @NotNull(message = "Please provide Location ID")
    private Long id;

    @NotBlank(message = "Please provide the name of the Location")
    private String locationName;

    @NotNull(message = "Please provide the latitude of the Location")
    private Double latitude;

    @NotNull(message = "Please provide the longitude of the Location")
    private Double longitude;
}
