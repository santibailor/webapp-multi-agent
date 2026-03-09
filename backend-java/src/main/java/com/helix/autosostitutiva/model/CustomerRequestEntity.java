package com.helix.autosostitutiva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Entity
@Table(name = "customer_request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;

    private String replacedVehiclePlate;

    private String deliveryType; // POINT_OF_DELIVERY, HOME_DELIVERY
    
    // Options
    private String gearbox;
    private Boolean snowChains;
    private Boolean doubleKeys;
    private Boolean isofix;
    private String notes;

    private String status; // In attesa, In consegna, Consegnata, Rifiutato
    private LocalDate requestDate;
    
    // Result representation
    private String assignedCarPlate;
    private LocalDate deliveryDate;
    private LocalDate returnDate;
}
