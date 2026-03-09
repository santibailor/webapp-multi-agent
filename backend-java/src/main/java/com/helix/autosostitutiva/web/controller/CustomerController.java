package com.helix.autosostitutiva.web.controller;

import com.helix.autosostitutiva.service.CustomerRequestService;
import com.helix.autosostitutiva.web.api.CustomerApi;
import com.helix.autosostitutiva.web.dto.CustomerRequestPage;
import com.helix.autosostitutiva.web.dto.NewCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerRequestService service;

    @Override
    public ResponseEntity<Void> customerRequestsPost(NewCustomerRequest newCustomerRequest) {
        service.createRequest(newCustomerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerRequestPage> customerRequestsGet(Integer page, Integer size, String sort) {
        // Mock email for now, ideally extracted from SecurityContext
        String customerEmail = "mario.rossi@gmail.com";
        CustomerRequestPage response = service.getCustomerRequests(customerEmail, page != null ? page : 0, size != null ? size : 10, sort);
        return ResponseEntity.ok(response);
    }
}
