package com.helix.autosostitutiva.repository;

import com.helix.autosostitutiva.model.CustomerRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequestEntity, Long> {

    @Query("SELECT r FROM CustomerRequestEntity r WHERE " +
           "LOWER(r.customerName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(r.customerEmail) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(r.replacedVehiclePlate) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<CustomerRequestEntity> searchRequests(String search, Pageable pageable);

    Page<CustomerRequestEntity> findByCustomerEmail(String email, Pageable pageable);
}
