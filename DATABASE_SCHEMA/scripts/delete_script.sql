DELETE FROM stocks_has_products WHERE products_id = 1;

DELETE FROM stocks_has_products WHERE stocks_id = 1;

DELETE FROM products WHERE id = 1;

DELETE FROM employees WHERE workshop_id = 1;

DELETE FROM stocks_has_products WHERE products_id = 4;

DELETE FROM roles WHERE id = 1;

DELETE FROM accounts WHERE employees_id = 1;

DELETE FROM bonus_payments WHERE monthly_payments_id IN (SELECT id FROM monthly_payments WHERE employees_id = 2);

DELETE FROM monthly_payments WHERE employees_id = 1;

DELETE FROM contracts WHERE employees_id = 2;

DELETE FROM employees WHERE id = 1;
