
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
    ('P005', 'Air Refreshener', 5.00);

INSERT INTO stocks_has_products (stocks_id, products_id, count)
VALUES 
	(2, 1, 1),
    (1, 2, 10),
    (1, 3, 15),
    (1, 4, 15),
    (1, 5, 30);


INSERT INTO employees (workshop_id, name, surname, phone_number, position) 
VALUES 
	(1, 'John', 'Doe', '123456789', 'Mechanic'),
    (1, 'Herbert', 'Smith', '123456789', 'Salesman');


INSERT INTO contracts (start_date, end_date, type, salary, active, employees_id) 
VALUES 
('2023-01-01', '2024-12-31', 'Full-time', 5000.00, 1, 1),
('2023-01-01', '2024-12-31', 'Full-time', 4000.00, 1, 2);


INSERT INTO monthly_payments (amount, payment_date, salary, employees_id) 
VALUES 
    ('5000.00', '2023-01-15', 5000.00, 1),
    ('5000.00', '2023-02-15', 5000.00, 1),
    ('5000.00', '2023-03-15', 5000.00, 1),
    ('5000.00', '2023-04-15', 5000.00, 1),
    ('5000.00', '2023-05-15', 5000.00, 1),
    ('5000.00', '2023-06-15', 5000.00, 1),
    ('5000.00', '2023-07-15', 5000.00, 1),
    ('5000.00', '2023-08-15', 5000.00, 1),
    ('5000.00', '2023-09-15', 5000.00, 1),
    ('5000.00', '2023-10-15', 5000.00, 1),
    ('5000.00', '2023-11-15', 5000.00, 1),
    ('5000.00', '2023-12-15', 5000.00, 1),
    
	('4000.00', '2023-01-15', 4000.00, 2),
    ('4000.00', '2023-02-15', 4000.00, 2),
    ('4000.00', '2023-03-15', 4000.00, 2),
    ('4000.00', '2023-04-15', 4000.00, 2),
    ('4000.00', '2023-05-15', 4000.00, 2),
    ('4000.00', '2023-06-15', 4000.00, 2),
    ('4000.00', '2023-07-15', 4000.00, 2),
    ('4000.00', '2023-08-15', 4000.00, 2),
    ('4000.00', '2023-09-15', 4000.00, 2),
    ('4000.00', '2023-10-15', 4000.00, 2),
    ('4000.00', '2023-11-15', 4000.00, 2),
    ('4000.00', '2023-12-15', 4000.00, 2);
    
INSERT INTO bonus_payments (amount, description, monthly_payments_id) VALUES 
(1000.00, 'Year-end bonus', 12),
(1000.00, 'Year-end bonus', 24);


INSERT INTO accounts (login, password, employees_id) 
VALUES 
	('john_doe', 'hashed_password', 1),
    ('herbert_smith', 'hashed_password', 2);

INSERT INTO roles (name) VALUES
    ('Admin'),
    ('Salesman'),
    ('Mechanic');

INSERT INTO reoles_has_accounts (roles_id, accounts_id) 
VALUES 
(1, 2),
(3,2);


