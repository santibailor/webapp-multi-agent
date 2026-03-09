package com.helix.autosostitutiva.service;

import com.helix.autosostitutiva.mapper.AutosostitutivaMapper;
import com.helix.autosostitutiva.model.CustomerRequestEntity;
import com.helix.autosostitutiva.repository.CustomerRequestRepository;
import com.helix.autosostitutiva.web.dto.CustomerRequestPage;
import com.helix.autosostitutiva.web.dto.NewCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerRequestService {

    private final CustomerRequestRepository repository;
    private final AutosostitutivaMapper mapper;

    @Transactional(readOnly = true)
    public CustomerRequestPage getCustomerRequests(String email, int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (sort != null && !sort.isEmpty()) {
            String[] parts = sort.split(",");
            Sort.Direction direction = parts.length > 1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageRequest = pageRequest.withSort(Sort.by(direction, parts[0]));
        }

        Page<CustomerRequestEntity> entityPage;
        if (email != null && !email.isEmpty()) {
            entityPage = repository.findByCustomerEmail(email, pageRequest);
        } else {
            entityPage = repository.findAll(pageRequest);
        }

        CustomerRequestPage response = new CustomerRequestPage();
        response.setContent(entityPage.getContent().stream().map(mapper::toCustomerRequest).toList());
        response.setTotalElements((int) entityPage.getTotalElements());
        response.setTotalPages(entityPage.getTotalPages());
        return response;
    }

    @Transactional
    public void createRequest(NewCustomerRequest request) {
        CustomerRequestEntity entity = mapper.toEntity(request);
        repository.save(entity);
    }
}
