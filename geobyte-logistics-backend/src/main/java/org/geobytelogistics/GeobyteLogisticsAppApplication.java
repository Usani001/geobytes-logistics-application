package org.geobytelogistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.geobytelogistics")
public class GeobyteLogisticsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeobyteLogisticsAppApplication.class, args);
    }

}
