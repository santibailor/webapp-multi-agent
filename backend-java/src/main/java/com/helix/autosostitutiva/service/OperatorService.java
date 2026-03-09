package com.helix.autosostitutiva.service;

import com.helix.autosostitutiva.mapper.AutosostitutivaMapper;
import com.helix.autosostitutiva.model.BookingEntity;
import com.helix.autosostitutiva.model.Car;
import com.helix.autosostitutiva.model.CustomerRequestEntity;
import com.helix.autosostitutiva.repository.BookingRepository;
import com.helix.autosostitutiva.repository.CarRepository;
import com.helix.autosostitutiva.repository.CustomerRequestRepository;
import com.helix.autosostitutiva.web.dto.AvailableCar;
import com.helix.autosostitutiva.web.dto.NewBooking;
import com.helix.autosostitutiva.web.dto.OperatorRequestPage;
import com.helix.autosostitutiva.web.dto.OperatorStats;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final CustomerRequestRepository requestRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final AutosostitutivaMapper mapper;

    @Transactional(readOnly = true)
    public OperatorStats getStats() {
        OperatorStats stats = new OperatorStats();
        long totalRequests = requestRepository.count();
        long totalBookings = bookingRepository.count();

        // Calculate total expenses arbitrarily for this demo
        BigDecimal totalExpenses = BigDecimal.valueOf(totalBookings * 150.0);

        stats.setTotalRequests((int) totalRequests);
        stats.setTotalReplacedCars((int) totalBookings);
        stats.setTotalExpenses(totalExpenses.floatValue());

        return stats;
    }

    @Transactional(readOnly = true)
    public OperatorRequestPage getRequests(int page, int size, String sort, String search) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (sort != null && !sort.isEmpty()) {
            String[] parts = sort.split(",");
            Sort.Direction direction = parts.length > 1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageRequest = pageRequest.withSort(Sort.by(direction, parts[0]));
        }

        Page<CustomerRequestEntity> entityPage;
        if (search != null && !search.isEmpty()) {
            entityPage = requestRepository.searchRequests(search, pageRequest);
        } else {
            entityPage = requestRepository.findAll(pageRequest);
        }

        OperatorRequestPage response = new OperatorRequestPage();
        response.setContent(entityPage.getContent().stream().map(mapper::toOperatorRequest).toList());
        response.setTotalElements((int) entityPage.getTotalElements());
        response.setTotalPages(entityPage.getTotalPages());
        return response;
    }

    @Transactional(readOnly = true)
    public List<AvailableCar> searchCars(LocalDate startDate, LocalDate endDate) {
        // Mock query - returning all cars for the mockup UI
        return carRepository.findAll().stream().map(mapper::toAvailableCar).toList();
    }

    @Transactional
    public void bookCar(NewBooking bookingDto) {
        Car car = carRepository.findById(bookingDto.getCarPlate())
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        BookingEntity booking = BookingEntity.builder()
                .car(car)
                .customerEmail(bookingDto.getCustomerEmail())
                .replacedVehiclePlate(bookingDto.getReplacedVehiclePlate())
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .build();

        bookingRepository.save(booking);
    }
}
