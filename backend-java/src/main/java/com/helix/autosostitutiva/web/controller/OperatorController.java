package com.helix.autosostitutiva.web.controller;

import com.helix.autosostitutiva.service.OperatorService;
import com.helix.autosostitutiva.web.api.OperatorApi;
import com.helix.autosostitutiva.web.dto.AvailableCar;
import com.helix.autosostitutiva.web.dto.NewBooking;
import com.helix.autosostitutiva.web.dto.OperatorRequestPage;
import com.helix.autosostitutiva.web.dto.OperatorStats;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperatorController implements OperatorApi {

    private final OperatorService service;

    @Override
    public ResponseEntity<Void> operatorBookingsPost(NewBooking newBooking) {
        service.bookCar(newBooking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OperatorStats> operatorDashboardStatsGet() {
        return ResponseEntity.ok(service.getStats());
    }

    @Override
    public ResponseEntity<OperatorRequestPage> operatorRequestsGet(Integer page, Integer size, String sort, String search) {
        OperatorRequestPage response = service.getRequests(page != null ? page : 0, size != null ? size : 10, sort, search);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<AvailableCar>> operatorVehiclesSearchGet(LocalDate startDate, LocalDate endDate) {
        List<AvailableCar> cars = service.searchCars(startDate, endDate);
        return ResponseEntity.ok(cars);
    }
}
