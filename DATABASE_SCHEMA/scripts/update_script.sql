UPDATE stocks_has_products SET count = count + 5 WHERE stocks_id = 2 AND products_id = 1;

UPDATE accounts SET login = 'john_doe_123' WHERE employees_id = 1;

UPDATE reoles_has_accounts SET roles_id = 2 WHERE accounts_id = 2 AND roles_id = 3;

UPDATE roles SET name = 'Lead Mechanic' WHERE id = 3;

UPDATE accounts SET password = 'new_hashed_password' WHERE id = 1;

UPDATE contracts SET active = 0 WHERE employees_id = 1;

UPDATE workshops SET name = 'Pablo\'s Auto Repair' WHERE id = 1;

UPDATE adresses SET city = 'Warsaw', street = 'Main', postal_code = '20-123' WHERE id = 1;

UPDATE employees SET name = 'Henry' WHERE id = 2;

UPDATE employees SET phone_number = '987654321' WHERE id = 1;