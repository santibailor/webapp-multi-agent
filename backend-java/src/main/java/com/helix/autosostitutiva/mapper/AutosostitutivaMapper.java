package com.helix.autosostitutiva.mapper;

import com.helix.autosostitutiva.model.Car;
import com.helix.autosostitutiva.model.CustomerRequestEntity;
import com.helix.autosostitutiva.web.dto.AvailableCar;
import com.helix.autosostitutiva.web.dto.CustomerRequest;
import com.helix.autosostitutiva.web.dto.NewCustomerRequest;
import com.helix.autosostitutiva.web.dto.OperatorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface AutosostitutivaMapper {

    @Mapping(target = "price", expression = "java(bigDecimalToFloat(car.getPrice()))")
    AvailableCar toAvailableCar(Car car);

    default Float bigDecimalToFloat(BigDecimal value) {
        return value != null ? value.floatValue() : null;
    }

    @Mapping(target = "id", expression = "java(String.format(\"%05d\", entity.getId()))")
    @Mapping(target = "plate", source = "replacedVehiclePlate")
    @Mapping(target = "vehicle", expression = "java(\"TBD\")")
    @Mapping(target = "fuel", expression = "java(\"TBD\")")
    @Mapping(target = "status", source = "status")
    CustomerRequest toCustomerRequest(CustomerRequestEntity entity);

    @Mapping(target = "id", expression = "java(String.format(\"%05d\", entity.getId()))")
    @Mapping(target = "plate", source = "replacedVehiclePlate")
    @Mapping(target = "vehicle", expression = "java(\"TBD\")")
    @Mapping(target = "fuel", expression = "java(\"TBD\")")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "customerName", source = "customerName")
    OperatorRequest toOperatorRequest(CustomerRequestEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerName", source = "customerInfo.name")
    @Mapping(target = "customerPhone", source = "customerInfo.phone")
    @Mapping(target = "customerEmail", source = "customerInfo.email")
    @Mapping(target = "customerAddress", source = "customerInfo.address")
    @Mapping(target = "gearbox", source = "options.gearbox")
    @Mapping(target = "snowChains", source = "options.snowChains")
    @Mapping(target = "doubleKeys", source = "options.doubleKeys")
    @Mapping(target = "isofix", source = "options.isofix")
    @Mapping(target = "notes", source = "options.notes")
    @Mapping(target = "status", constant = "In attesa")
    @Mapping(target = "requestDate", expression = "java(java.time.LocalDate.now())")
    CustomerRequestEntity toEntity(NewCustomerRequest request);
}
