DROP DATABASE IF EXISTS solvd_database;

CREATE DATABASE IF NOT EXISTS solvd_database;
USE solvd_database;

CREATE TABLE IF NOT EXISTS adresses
  (
     id              SERIAL,-- BIGINT UNSIGNED NOT NULL AUTOINCREMENT UNIQUE
     city            VARCHAR(45) NOT NULL,
     street          VARCHAR(45) NOT NULL,
     building_number VARCHAR(45) NOT NULL,
     postal_code     VARCHAR(45) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS workshops
  (
     id          SERIAL,
     adresses_id BIGINT UNSIGNED NOT NULL,
     name        VARCHAR(45) NOT NULL,
     nip         VARCHAR(45) NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_workshops_adresses FOREIGN KEY (adresses_id) REFERENCES
     adresses (id) ON DELETE CASCADE ON UPDATE no action
  );

CREATE TABLE IF NOT EXISTS stocks
  (
     id           SERIAL,
     name         VARCHAR(45) NOT NULL,
     workshops_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_stocks_workshops_id FOREIGN KEY (workshops_id) REFERENCES
     workshops (id) ON DELETE CASCADE ON UPDATE no action
  );
  
  CREATE TABLE IF NOT EXISTS products
  (
     id             SERIAL,
     product_number VARCHAR(45) NOT NULL,
     name           VARCHAR(45) NOT NULL,
     price          DOUBLE NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS stocks_has_products
  (
     stocks_id   BIGINT UNSIGNED NOT NULL,
     products_id BIGINT UNSIGNED NOT NULL,
     count       TINYINT(255) UNSIGNED NOT NULL,
     PRIMARY KEY (stocks_id, products_id),
     CONSTRAINT fk_stocks_has_products_stocks FOREIGN KEY (stocks_id) REFERENCES
     stocks (id) ON DELETE no action ON UPDATE no action,
     CONSTRAINT fk_stocks_has_products_products1 FOREIGN KEY (products_id)
     REFERENCES products (id) ON DELETE no action ON UPDATE no action
  );

CREATE TABLE IF NOT EXISTS employees
  (
     id           SERIAL,
     workshop_id  BIGINT UNSIGNED NOT NULL,
     name         VARCHAR(45) NOT NULL,
     surname      VARCHAR(45) NOT NULL,
     phone_number VARCHAR(45) NOT NULL,
     position     VARCHAR(45) NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_employees_workshop_id FOREIGN KEY (workshop_id) REFERENCES
     workshops (id) ON DELETE CASCADE ON UPDATE no action
  );

CREATE TABLE IF NOT EXISTS contracts
  (
     id           BIGINT UNSIGNED NOT NULL auto_increment,
     start_date   DATE NOT NULL,
     end_date     DATE NOT NULL,
     type         VARCHAR(45) NOT NULL,
     salary       DOUBLE NOT NULL,
     active       TINYINT NOT NULL,
     employees_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_contracts_employees FOREIGN KEY (employees_id) REFERENCES
     employees (id) ON DELETE CASCADE ON UPDATE no action
  );

CREATE TABLE IF NOT EXISTS monthly_payments
  (
     id           BIGINT UNSIGNED NOT NULL auto_increment,
     amount       DOUBLE NOT NULL,
     payment_date DATE NOT NULL,
     employees_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_monthly_payments_employees FOREIGN KEY (employees_id)
     REFERENCES employees (id) ON DELETE CASCADE ON UPDATE no action
  );

CREATE TABLE IF NOT EXISTS bonus_payments
  (
     id                  BIGINT UNSIGNED NOT NULL auto_increment,
     amount              DOUBLE NOT NULL,
     description         VARCHAR(45) NOT NULL,
     monthly_payments_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_bonus_payments_monthly_payments FOREIGN KEY (
     monthly_payments_id) REFERENCES monthly_payments (id) ON DELETE CASCADE ON
     UPDATE no action
  );

CREATE TABLE IF NOT EXISTS accounts
  (
     id           BIGINT UNSIGNED NOT NULL auto_increment,
     login        VARCHAR(45) NOT NULL,
     password     VARCHAR(256) NOT NULL,
     employees_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_accounts_employees FOREIGN KEY (employees_id) REFERENCES
     employees (id) ON DELETE CASCADE ON UPDATE no action
  );
  
  CREATE TABLE IF NOT EXISTS roles
  (
     id   SERIAL,
     name VARCHAR(45),
     PRIMARY KEY (id)
  ); 

CREATE TABLE IF NOT EXISTS roles_has_accounts
  (
     roles_id    BIGINT UNSIGNED NOT NULL,
     accounts_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (roles_id, accounts_id),
     CONSTRAINT fk_roles_has_accounts_roles FOREIGN KEY (roles_id) REFERENCES
     roles (id) ON DELETE CASCADE ON UPDATE no action,
     CONSTRAINT fk_roles_has_accounts_accounts FOREIGN KEY (accounts_id)
     REFERENCES accounts (id) ON DELETE CASCADE ON UPDATE no action
  );

CREATE TABLE IF NOT EXISTS invoices
  (
     id          SERIAL,
     date_time   TIMESTAMP NOT NULL,
     total_price DOUBLE NOT NULL,
     PRIMARY KEY (id)
  );
  
  CREATE TABLE IF NOT EXISTS invoices_has_products (
  invoices_id BIGINT UNSIGNED NOT NULL,
  products_id BIGINT UNSIGNED,
  PRIMARY KEY (invoices_id, products_id),
  CONSTRAINT fk_invoices_has_products_invoices
    FOREIGN KEY (invoices_id)
    REFERENCES invoices (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_invoices_has_products_products
    FOREIGN KEY (products_id)
    REFERENCES products (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS customers
  (
     id           SERIAL,
     name         VARCHAR(45) NOT NULL,
     surname      VARCHAR(45) NOT NULL,
     email        VARCHAR(45) NOT NULL,
     phone_number VARCHAR(45) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS vehicles
  (
     id            SERIAL,
     vin           VARCHAR(17) NOT NULL,
     make          VARCHAR(45) NOT NULL,
     model         VARCHAR(45) NOT NULL,
     license_plate VARCHAR(45) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS service_orders
  (
     id           BIGINT UNSIGNED NOT NULL auto_increment,
     vehicles_id  BIGINT UNSIGNED NOT NULL,
     customers_id BIGINT UNSIGNED NOT NULL,
     date         DATE NOT NULL,
     completed    TINYINT(1) NOT NULL,
     invoices_id  BIGINT UNSIGNED NULL,
     workshop_id  BIGINT UNSIGNED NOT NULL,
     employees_id BIGINT UNSIGNED NOT NULL,
     description VARCHAR(500) NOT NULL,
     PRIMARY KEY (id),
     
     CONSTRAINT fk_service_orders_vehicles FOREIGN KEY (vehicles_id) REFERENCES
     vehicles (id) ON DELETE CASCADE ON UPDATE no action,
     
     CONSTRAINT fk_service_orders_customers1 FOREIGN KEY (customers_id)
     REFERENCES customers (id) ON DELETE CASCADE ON UPDATE no action,
     
     CONSTRAINT fk_service_orders_invoices FOREIGN KEY (invoices_id) REFERENCES
     invoices (id) ON DELETE CASCADE ON UPDATE no action,
     
     CONSTRAINT fk_service_orders_workshop FOREIGN KEY (workshop_id) REFERENCES
     workshops (id) ON DELETE CASCADE ON UPDATE no action,
     
     CONSTRAINT fk_service_orders_employees FOREIGN KEY (employees_id)
     REFERENCES employees (id) ON DELETE no action ON UPDATE no action
  );