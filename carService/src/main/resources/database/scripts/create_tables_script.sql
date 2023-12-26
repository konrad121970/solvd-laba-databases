drop database if exists solvd_database;

create database if not exists solvd_database;

use solvd_database;

create table if not exists adresses
  (
     id              SERIAL,-- BIGINT UNSIGNED NOT NULL AUTOINCREMENT UNIQUE
     city            varchar(45) not null,
     street          varchar(45) not null,
     building_number varchar(45) not null,
     postal_code     varchar(45) not null,
     primary key (id)
  );

create table if not exists workshops
  (
     id          SERIAL,
     adresses_id bigint unsigned not null,
     name        varchar(45) not null,
     nip         varchar(45) not null,
     primary key (id),
     constraint fk_workshops_adresses foreign key (adresses_id) references
     adresses (id) on delete cascade on update no action
  );

create table if not exists stocks
  (
     id           SERIAL,
     name         varchar(45) not null,
     workshops_id bigint unsigned not null,
     primary key (id),
     constraint fk_stocks_workshops_id foreign key (workshops_id) references
     workshops (id) on delete cascade on update no action
  );

create table if not exists products
  (
     id             SERIAL,
     product_number varchar(45) not null,
     name           varchar(45) not null,
     price          double not null,
     primary key (id)
  );

create table if not exists stocks_has_products
  (
     stocks_id   bigint unsigned not null,
     products_id bigint unsigned not null,
     count       tinyint(255) unsigned not null,
     primary key (stocks_id, products_id),
     constraint fk_stocks_has_products_stocks foreign key (stocks_id) references
     stocks (id) on delete cascade on update no action,
     constraint fk_stocks_has_products_products1 foreign key (products_id)
     references products (id) on delete cascade on update no action
  );

create table if not exists employees
  (
     id           SERIAL,
     workshop_id  bigint unsigned not null,
     name         varchar(45) not null,
     surname      varchar(45) not null,
     phone_number varchar(45) not null,
     position     varchar(45) not null,
     primary key (id),
     constraint fk_employees_workshop_id foreign key (workshop_id) references
     workshops (id) on delete cascade on update no action
  );

create table if not exists contracts
  (
     id           bigint unsigned not null auto_increment,
     start_date   DATE not null,
     end_date     DATE not null,
     type         varchar(45) not null,
     salary       double not null,
     active       tinyint not null,
     employees_id bigint unsigned not null,
     primary key (id),
     constraint fk_contracts_employees foreign key (employees_id) references
     employees (id) on delete cascade on update no action
  );

create table if not exists monthly_payments
  (
     id           bigint unsigned not null auto_increment,
     amount       double not null,
     payment_date DATE not null,
     employees_id bigint unsigned not null,
     primary key (id),
     constraint fk_monthly_payments_employees foreign key (employees_id)
     references employees (id) on delete cascade on update no action
  );

create table if not exists bonus_payments
  (
     id                  bigint unsigned not null auto_increment,
     amount              double not null,
     description         varchar(45) not null,
     monthly_payments_id bigint unsigned not null,
     primary key (id),
     constraint fk_bonus_payments_monthly_payments foreign key (
     monthly_payments_id) references monthly_payments (id) on delete cascade on
     update no action
  );

create table if not exists accounts
  (
     id           bigint unsigned not null auto_increment,
     login        varchar(45) not null,
     password     varchar(256) not null,
     employees_id bigint unsigned not null,
     primary key (id),
     constraint fk_accounts_employees foreign key (employees_id) references
     employees (id) on delete cascade on update no action
  );

create table if not exists roles
  (
     id   SERIAL,
     name varchar(45),
     primary key (id)
  );

create table if not exists roles_has_accounts
  (
     roles_id    bigint unsigned not null,
     accounts_id bigint unsigned not null,
     primary key (roles_id, accounts_id),
     constraint fk_roles_has_accounts_roles foreign key (roles_id) references
     roles (id) on delete cascade on update no action,
     constraint fk_roles_has_accounts_accounts foreign key (accounts_id)
     references accounts (id) on delete cascade on update no action
  );

create table if not exists invoices
  (
     id          SERIAL,
     date_time   TIMESTAMP not null,
     total_price double not null,
     primary key (id)
  );

create table if not exists invoices_has_products
  (
     invoices_id bigint unsigned not null,
     products_id bigint unsigned,
     primary key (invoices_id, products_id),
     constraint fk_invoices_has_products_invoices foreign key (invoices_id)
     references invoices (id) on delete cascade on update no action,
     constraint fk_invoices_has_products_products foreign key (products_id)
     references products (id) on delete cascade on update no action
  );

create table if not exists customers
  (
     id           SERIAL,
     name         varchar(45) not null,
     surname      varchar(45) not null,
     email        varchar(45) not null,
     phone_number varchar(45) not null,
     primary key (id)
  );

create table if not exists vehicles
  (
     id            SERIAL,
     vin           varchar(17) not null,
     make          varchar(45) not null,
     model         varchar(45) not null,
     license_plate varchar(45) not null,
     primary key (id)
  );

create table if not exists service_orders
  (
     id           bigint unsigned not null auto_increment,
     vehicles_id  bigint unsigned not null,
     customers_id bigint unsigned not null,
     date         DATE not null,
     completed    tinyint(1) not null,
     invoices_id  bigint unsigned null,
     workshop_id  bigint unsigned not null,
     employees_id bigint unsigned not null,
     description  varchar(500) not null,
     primary key (id),
     constraint fk_service_orders_vehicles foreign key (vehicles_id) references
     vehicles (id) on delete cascade on update no action,
     constraint fk_service_orders_customers1 foreign key (customers_id)
     references customers (id) on delete cascade on update no action,
     constraint fk_service_orders_invoices foreign key (invoices_id) references
     invoices (id) on delete cascade on update no action,
     constraint fk_service_orders_workshop foreign key (workshop_id) references
     workshops (id) on delete cascade on update no action,
     constraint fk_service_orders_employees foreign key (employees_id)
     references employees (id) on delete cascade on update no action
  ); 