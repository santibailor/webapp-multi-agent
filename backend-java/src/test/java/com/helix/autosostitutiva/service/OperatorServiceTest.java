package com.helix.autosostitutiva.service;

import com.helix.autosostitutiva.mapper.AutosostitutivaMapper;
import com.helix.autosostitutiva.repository.BookingRepository;
import com.helix.autosostitutiva.repository.CarRepository;
import com.helix.autosostitutiva.repository.CustomerRequestRepository;
import com.helix.autosostitutiva.web.dto.OperatorStats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperatorServiceTest {

    @Mock
    private CustomerRequestRepository requestRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private AutosostitutivaMapper mapper;

    @InjectMocks
    private OperatorService operatorService;

    @Test
    void shouldReturnCorrectOperatorStats() {
        when(requestRepository.count()).thenReturn(10L);
        when(bookingRepository.count()).thenReturn(5L);

        OperatorStats stats = operatorService.getStats();

        assertEquals(10, stats.getTotalRequests());
        assertEquals(5, stats.getTotalReplacedCars());
        // 5 bookings * 150 = 750
        assertEquals(750.0f, stats.getTotalExpenses());
    }
}
