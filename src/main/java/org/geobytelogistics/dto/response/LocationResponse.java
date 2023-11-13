package org.geobytelogistics.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private Long id;

    private String locationName;

    private double latitude;

    private double longitude;
}
