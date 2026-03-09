CREATE TABLE car (
    plate VARCHAR(20) PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    seats INTEGER NOT NULL,
    fuel VARCHAR(50) NOT NULL,
    gearbox VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE customer_request (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(50) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_address VARCHAR(500),
    replaced_vehicle_plate VARCHAR(20) NOT NULL,
    delivery_type VARCHAR(50) NOT NULL,
    gearbox VARCHAR(50),
    snow_chains BOOLEAN,
    double_keys BOOLEAN,
    isofix BOOLEAN,
    notes TEXT,
    status VARCHAR(50) NOT NULL,
    request_date DATE NOT NULL,
    assigned_car_plate VARCHAR(20),
    delivery_date DATE,
    return_date DATE
);

CREATE TABLE booking (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_email VARCHAR(255) NOT NULL,
    replaced_vehicle_plate VARCHAR(20) NOT NULL,
    car_plate VARCHAR(20) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (car_plate) REFERENCES car(plate)
);

-- Seed some mock cars
INSERT INTO car (plate, brand, model, type, seats, fuel, gearbox, price) VALUES
('AB000CD', 'Volkswagen', 'Polo', 'City Car', 5, 'Benzina', 'Manuale', 50.00),
('GH333LM', 'Fiat', 'Panda', 'City Car', 5, 'Diesel', 'Manuale', 35.00),
('CD222EF', 'Nissan', 'Juke', 'SUV', 5, 'Ibrida', 'Automatico', 70.00);
