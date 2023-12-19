
INSERT INTO adresses (city, street, building_number, postal_code) 
VALUES 
	('Bialystok', 'Zwierzyniecka', '12', '17-200');


INSERT INTO workshops (adresses_id, name, nip) 
VALUES 
	(1, 'Konrad\'s Car Service', '0123456789');


INSERT INTO stocks (name, workshops_id) 
VALUES 
	('Shop', 1),
    ('Warehouse', 1);

INSERT INTO products (product_number, name, price)
VALUES
    ('P001', 'Flywheel', 300.00),
    ('P002', 'Oil Filter', 10.00),
    ('P003', 'Air Filter', 50.00),
    ('P004', 'Cabin Filter', 20.00),
    ('P005', 'Air Refreshener', 5.00),
    ('P006', 'Brake Pads', 40.00),
    ('P007', 'Spark Plugs', 8.00),
    ('P008', 'Engine Oil', 25.00),
    ('P009', 'Transmission Fluid', 15.00),
    ('P010', 'Windshield Wipers', 12.00),
    ('P011', 'Michelin 245/45R17', 300.00),
    ('P012', 'Battery', 205.00),
    ('P013', 'Headlamp', 145.00);

INSERT INTO stocks_has_products (stocks_id, products_id, count)
VALUES 
	(2, 1, 1),
    (1, 2, 10),
    (1, 3, 15),
    (1, 4, 15),
    (1, 5, 30),
	(1, 6, 20),
    (1, 7, 50),
    (1, 8, 30),
    (1, 9, 25),
    (1, 10, 40),
    (2, 11, 12),
    (2, 12, 13);

INSERT INTO employees (workshop_id, name, surname, phone_number, position) 
VALUES 
	(1, 'John', 'Doe', '123456789', 'Mechanic'),
    (1, 'Herbert', 'Smith', '123456789', 'Salesman'),
	(1, 'Alice', 'Johnson', '987654321', 'Mechanic'),
    (1, 'David', 'Williams', '876543210', 'Accountant'),
    (1, 'David', 'Davis', '654321098', 'Mechanic');
    
INSERT INTO accounts (login, password, employees_id) 
VALUES 
	('john_doe', 'hashed_password', 1),
    ('herbert_smith', 'hashed_password', 2),
    ('alice_johnson', 'hashed_password', 3),
    ('david_williams', 'hashed_password', 4),
    ('david_davis', 'hashed_password', 5);

INSERT INTO roles (name) 
VALUES
    ('Salesman'),
    ('Mechanic'),
    ('Accountant');

INSERT INTO roles_has_accounts (roles_id, accounts_id) 
VALUES 
	(2, 1), -- Mechanic role for John Doe
    (1, 2), -- Salesman role for Herbert Smith
    (2, 3), -- Mechanic role for Alice Johnson
    (3, 4), -- Accountant role for David Williams
    (2, 5); -- Manager role for David Davis


INSERT INTO contracts (start_date, end_date, type, salary, active, employees_id) 
VALUES 
('2023-01-01', '2024-12-31', 'Full-time', 5000.00, 1, 1),
('2023-01-01', '2024-12-31', 'Full-time', 4000.00, 1, 2),
('2023-01-01', '2024-12-31', 'Full-time', 4800.00, 1, 3),
('2023-01-01', '2024-12-31', 'Part-time', 3000.00, 1, 4),
('2023-01-01', '2024-12-31', 'Full-time', 5500.00, 1, 5);

INSERT INTO monthly_payments (amount, payment_date, employees_id) 
VALUES 
    (5000, '2023-01-15', 1),
    (5000, '2023-02-15', 1),
    (5000, '2023-03-15', 1),
    (5000, '2023-04-15', 1),
    (5000, '2023-05-15', 1),
    (5000, '2023-06-15', 1),
    (5000, '2023-07-15', 1),
    (5000, '2023-08-15', 1),
    (5000, '2023-09-15', 1),
    (5000, '2023-10-15', 1),
    (5000, '2023-11-15', 1),
    (5000, '2023-12-15', 1),
    
    (4000, '2023-01-15', 2),
    (4000, '2023-02-15', 2),
    (4000, '2023-03-15', 2),
    (4000, '2023-04-15', 2),
    (4000, '2023-05-15', 2),
    (4000, '2023-06-15', 2),
    (4000, '2023-07-15', 2),
    (4000, '2023-08-15', 2),
    (4000, '2023-09-15', 2),
    (4000, '2023-10-15', 2),
    (4000, '2023-11-15', 2),
    (4000, '2023-12-15', 2),
    
    (5500, '2023-01-15', 3),
    (5500, '2023-02-15', 3),
    (5500, '2023-03-15', 3),
    (5500, '2023-04-15', 3),
    (5500, '2023-05-15', 3),
    (5500, '2023-06-15', 3),
    (5500, '2023-07-15', 3),
    (5500, '2023-08-15', 3),
    (5500, '2023-09-15', 3),
    (5500, '2023-10-15', 3),
    (5500, '2023-11-15', 3),
    (5500, '2023-12-15', 3),
    
	(3000, '2023-01-15', 4),
    (3000, '2023-02-15', 4),
    (3000, '2023-03-15', 4),
    (3000, '2023-04-15', 4),
    (3000, '2023-05-15', 4),
    (3000, '2023-06-15', 4),
    (3000, '2023-07-15', 4),
    (3000, '2023-08-15', 4),
    (3000, '2023-09-15', 4),
    (3000, '2023-10-15', 4),
    (3000, '2023-11-15', 4),
    (3000, '2023-12-15', 4),

    (5500, '2023-01-15', 5),
    (5500, '2023-02-15', 5),
	(5500, '2023-03-15', 5),
	(5500, '2023-04-15', 5),
    (5500, '2023-05-15', 5),
    (5500, '2023-06-15', 5),
	(5500, '2023-07-15', 5),
    (5500, '2023-08-15', 5),
    (5500, '2023-09-15', 5),
	(5500, '2023-10-15', 5),
    (5500, '2023-11-15', 5),
    (5500, '2023-12-15', 5);
    
INSERT INTO bonus_payments (amount, description, monthly_payments_id) VALUES 
(1000.00, 'Year-end bonus', 12),
(1000.00, 'Year-end bonus', 24),
(800.00, 'Performance bonus', 5),
(800.00, 'Performance bonus', 15),
(800.00, 'Performance bonus', 27),
(500.00, 'Performance bonus', 37),
(1000.00, 'Performance bonus', 2),
(1000.00, 'Performance bonus', 13),
(1000.00, 'Performance bonus', 14);

INSERT INTO vehicles (vin, make, model, license_plate)
VALUES 
    ('1HGCM82633A654321', 'Toyota', 'Camry', 'MNO456'),
    ('2C3CDZC98FH654321', 'Ford', 'Mustang', 'PQR789'),
    ('1GNEK13RXTJ654321', 'Jeep', 'Wrangler', 'STU012'),
    ('5J6RM4H58FL654321', 'Nissan', 'Altima', 'VWX345'),
    ('3VWD07AJ2EM654321', 'Hyundai', 'Elantra', 'YZA678'),
    ('1HGCM82633A987654', 'Subaru', 'Outback', 'BCD901'),
    ('2C3CDZC98FH987654', 'Mazda', 'CX-5', 'EFG234'),
    ('1GNEK13RXTJ987654', 'Kia', 'Sorento', 'HIJ567'),
    ('5J6RM4H58FL987654', 'Buick', 'Enclave', 'KLM890'),
    ('3VWD07AJ2EM987654', 'Audi', 'A4', 'NOP123');

INSERT INTO customers (name, surname, email, phone_number)
VALUES
    ('Michael', 'Johnson', 'michael.johnson@example.com', '123456789'),
    ('Emma', 'Williams', 'emma.williams@example.com', '987654321'),
    ('Christopher', 'Smith', 'christopher.smith@example.com', '555555555'),
    ('Sophia', 'Brown', 'sophia.brown@example.com', '999888777'),
    ('Aiden', 'Miller', 'aiden.miller@example.com', '444333222'),
    ('Olivia', 'Anderson', 'olivia.anderson@example.com', '111000999'),
    ('Daniel', 'Harris', 'daniel.harris@example.com', '666777888'),
    ('Isabella', 'Davis', 'isabella.davis@example.com', '222333444'),
    ('Mason', 'Taylor', 'mason.taylor@example.com', '888999000'),
    ('Ava', 'Clark', 'ava.clark@example.com', '333444555');

INSERT INTO invoices (date_time, total_price)
VALUES
    ('2023-01-10 12:30:00', 350.00),
    ('2023-02-12 14:45:00', 200.00),
    ('2023-04-18 10:15:00', 100.00),
    ('2023-06-22 16:20:00', 450.00),
    ('2023-08-28 09:00:00', 300.00),
    ('2023-10-05 11:55:00', 150.00),
    ('2023-10-06 11:55:00', 50.00);
    
    INSERT INTO invoices_has_products (invoices_id, products_id)
VALUES
    (1, 1), -- Invoice 1 has Flywheel
    (1, 2), -- Invoice 1 has Oil Filter
    (2, 3), -- Invoice 2 has Air Filter
    (2, 4), -- Invoice 2 has Cabin Filter
    (3, 5), -- Invoice 3 has Air Refreshener
    (3, 6), -- Invoice 3 has Brake Pads
    (3, 7), -- Invoice 3 has Spark Plugs
    (4, 8), -- Invoice 4 has Engine Oil
    (4, 9), -- Invoice 4 has Transmission Fluid
    (5, 10), -- Invoice 5 has Windshield Wipers
    (6, 11); -- Invoice 6 has Michelin 245/45R17

INSERT INTO service_orders (vehicles_id, customers_id, date, completed, invoices_id, workshop_id, employees_id, description)
VALUES
    (1, 1, '2023-01-10', 1, 1, 1, 1, 'Description for Service Order 1'),
    (2, 2, '2023-02-12', 1, 2, 1, 1, 'Description for Service Order 2'),
    (3, 3, '2023-03-15', 0, NULL, 1, 5, 'Description for Service Order 3'),
    (4, 4, '2023-04-18', 1, 3, 1, 3, 'Description for Service Order 4'),
    (5, 5, '2023-05-20', 0, NULL, 1, 3, 'Description for Service Order 5'),
    (6, 6, '2023-06-22', 1, 4, 1, 1, 'Description for Service Order 6'),
    (7, 7, '2023-07-25', 0, NULL, 1, 3, 'Description for Service Order 7'),
    (8, 8, '2023-08-28', 1, 5, 1, 5, 'Description for Service Order 8'),
    (9, 9, '2023-09-30', 0, NULL, 1, 5, 'Description for Service Order 9'),
    (10, 10, '2023-10-05', 1, 6, 1, 3, 'Description for Service Order 10'),
    (10, 10, '2023-10-05', 1, 6, 1, 5, 'Description for Service Order 10');
    

