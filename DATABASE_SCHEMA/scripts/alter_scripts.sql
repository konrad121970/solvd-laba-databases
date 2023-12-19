ALTER TABLE employees
DROP COLUMN phone_number;

ALTER TABLE accounts
ADD COLUMN is_admin TINYINT(1) DEFAULT 0;

ALTER TABLE invoices
CHANGE COLUMN total_price final_price DOUBLE NOT NULL;

ALTER TABLE customers
ADD COLUMN age VARCHAR(100) NOT NULL;

ALTER TABLE bonus_payments
MODIFY COLUMN amount VARCHAR(100) NOT NULL;

ALTER TABLE roles_has_accounts
RENAME TO accounts_roles;
