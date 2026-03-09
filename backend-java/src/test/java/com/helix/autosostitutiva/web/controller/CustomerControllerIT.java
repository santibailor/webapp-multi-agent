package com.helix.autosostitutiva.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helix.autosostitutiva.model.Car;
import com.helix.autosostitutiva.model.CustomerRequestEntity;
import com.helix.autosostitutiva.repository.CarRepository;
import com.helix.autosostitutiva.repository.CustomerRequestRepository;
import com.helix.autosostitutiva.web.dto.CustomerInfo;
import com.helix.autosostitutiva.web.dto.NewCustomerRequest;
import com.helix.autosostitutiva.web.dto.RequestOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRequestRepository requestRepository;

    @BeforeEach
    void setUp() {
        requestRepository.deleteAll();
    }

    @Test
    void shouldCreateNewCustomerRequest() throws Exception {
        NewCustomerRequest request = new NewCustomerRequest();
        request.setReplacedVehiclePlate("AB123CD");
        request.setDeliveryType(NewCustomerRequest.DeliveryTypeEnum.HOME_DELIVERY);
        
        CustomerInfo info = new CustomerInfo();
        info.setName("Mario Rossi");
        info.setEmail("mario.rossi@gmail.com");
        info.setPhone("1234567890");
        info.setAddress("Roma");
        request.setCustomerInfo(info);

        RequestOptions options = new RequestOptions();
        options.setGearbox(RequestOptions.GearboxEnum.MANUALE);
        request.setOptions(options);

        mockMvc.perform(post("/api/v1/customer/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnCustomerRequestsWithPaginationAndSorting() throws Exception {
        // Seed
        CustomerRequestEntity req1 = CustomerRequestEntity.builder()
                .customerName("Mario")
                .customerEmail("mario.rossi@gmail.com")
                .customerPhone("111")
                .replacedVehiclePlate("AA111AA")
                .deliveryType("POINT")
                .status("In attesa")
                .requestDate(LocalDate.now())
                .build();
        requestRepository.save(req1);

        CustomerRequestEntity req2 = CustomerRequestEntity.builder()
                .customerName("Mario")
                .customerEmail("mario.rossi@gmail.com")
                .customerPhone("111")
                .replacedVehiclePlate("BB222BB")
                .deliveryType("POINT")
                .status("Consegnata")
                .requestDate(LocalDate.now())
                .build();
        requestRepository.save(req2);

        // Sorting by plate desc should return BB222BB first
        mockMvc.perform(get("/api/v1/customer/requests")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "replacedVehiclePlate,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].plate", is("BB222BB")))
                .andExpect(jsonPath("$.totalElements", is(2)));
    }
}
