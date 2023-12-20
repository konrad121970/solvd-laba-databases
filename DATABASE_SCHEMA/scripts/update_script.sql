
UPDATE stocks_has_products
SET count = count + 5
WHERE stocks_id = (SELECT id FROM stocks WHERE name = 'Shop')
AND products_id = (SELECT id FROM products WHERE product_number = 'P002');

UPDATE roles
SET name = 'Lead Mechanic'
WHERE name = 'Mechanic';

UPDATE accounts
SET password = 'new_hashed_password'
WHERE employees_id = (SELECT id FROM employees WHERE surname = 'Doe');

UPDATE contracts
SET active = 0
WHERE employees_id = (SELECT id FROM employees WHERE surname = 'Doe');

UPDATE accounts
SET login = 'john_doe_123'
WHERE employees_id = (SELECT id FROM employees WHERE surname = 'Doe');

UPDATE workshops
SET name = 'Auto garage'
WHERE name = 'Pablo\'s Auto Repair';

UPDATE adresses
SET city = 'Warsaw', street = 'Main', postal_code = '20-123'
WHERE city = 'Bialystok';

UPDATE employees
SET name = 'Henry'
WHERE surname = 'Doe';

UPDATE employees
SET phone_number = '987654321'
WHERE surname = 'Doe';