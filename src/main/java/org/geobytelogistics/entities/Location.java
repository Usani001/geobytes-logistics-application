package org.geobytelogistics.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_location")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;



}
