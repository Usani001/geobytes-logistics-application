package org.geobytelogistics.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "package")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column()
    private String packageName;

}
