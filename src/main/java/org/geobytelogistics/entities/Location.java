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

    @Column()
    private String locationName;

    @Column()
    private double latitude;

    @Column()
    private double longitude;



}
