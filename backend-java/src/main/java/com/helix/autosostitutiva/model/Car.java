package com.helix.autosostitutiva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Entity
@Table(name = "car")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    private String plate;
    private String brand;
    private String model;
    private String type;
    private Integer seats;
    private String fuel;
    private String gearbox;
    private BigDecimal price;
}
