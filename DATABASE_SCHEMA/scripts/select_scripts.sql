use solvd_database;

-- Select all service orders (also not completed)
select so.date as Service_order_date, so.completed Service_order_completed, c.name as Customer_name, c.surname as Customer_surname, v.make as Vehicle_make, v.model as Vehicle_model, i.date_time as Invoice_Date, i.total_price as Invoice_Price, e.name as Assigned_employee_name, e.surname as Assigned_employee_surname
from service_orders so
join customers c on so.customers_id = c.id
join vehicles v on so.vehicles_id = v.id
join employees e on so.employees_id = e.id
left join invoices i on so.invoices_id = i.id;

-- Select all service orders (only completed)
select so.date as Service_order_date, so.completed Service_order_completed, c.name as Customer_name, c.surname as Customer_surname, v.make as Vehicle_make, v.model as Vehicle_model, i.date_time as Invoice_Date, i.total_price as Invoice_Price, e.name as Assigned_employee_name, e.surname as Assigned_employee_surname
from service_orders so
join customers c on so.customers_id = c.id
join vehicles v on so.vehicles_id = v.id
join employees e on so.employees_id = e.id
join invoices i on so.invoices_id = i.id;

-- List vehicles that were serviced by customers
select so.id as order_id, so.date, c.name as customer_name, v.make as vehicle_make
from service_orders so
inner join customers c on so.customers_id = c.id
inner join vehicles v on so.vehicles_id = v.id;

-- select all invoices that don't contain products
select i.id as Invoice_ID, i.total_price as Price
from invoices i
left join invoices_has_products ihp on i.id = ihp.invoices_id
where ihp.invoices_id is null;

-- select all products that heven't been assigned to an invoice
select p.id as product_id, p.name as product_name, p.price as product_price
from products p
left join invoices_has_products ihp on p.id = ihp.products_id
where ihp.products_id is null;

-- same as above but using right join
select p.id as product_id, p.name as product_name, p.price as product_price
from invoices_has_products ihp
right join products p on p.id = ihp.products_id
where ihp.products_id is null;

-- *********************************************************
-- 7 statements with aggregate functions + group by + having:
-- show employees with count of assigned orders higher than 0
select e.id, e.name, e.surname, e.position, Count(so.id) as order_count
from employees e
left join service_orders so on e.id = so.employees_id
group by e.id
having order_count > 0
order by order_count desc;

-- show how many products do workshops have in their stocks
select w.name as workshop, s.name as stock_name, Count(p.id) as product_count
from workshops w
join stocks s on w.id = s.workshops_id
join stocks_has_products shp on s.id = shp.stocks_id
join products p on shp.products_id = p.id
group by w.name, s.name
having product_count > 0;

-- show average price of product in each stock when avg price is higher than 10
select s.name, Avg(p.price) as average_product_price
from stocks s
join stocks_has_products shp on s.id = shp.stocks_id
join products p on p.id = shp.products_id
group by s.name
having average_product_price > 10;

-- show average bonus amount that is higher than 500
select bp.description, Avg(bp.amount) as average_bonus
from bonus_payments bp
group by bp.description
having average_bonus > 500;

-- show value of products in each stock higher than 2000
select s.id as stock_id, s.name as stock_name, Sum(p.price * shp.count) as total_value
from stocks s
join stocks_has_products shp on s.id = shp.stocks_id
join products p on shp.products_id = p.id
group by s.id, s.name
having total_value > 2000;

-- customers with more than 1 assigned invoices
select c.name, Count(i.id) as invoice_count
from customers c
join service_orders so on c.id = so.customers_id
left join invoices i on i.id = so.invoices_id
group by c.name
having Count(i.id) > 1;

-- find employees that have been paid more than 100k 
select e.name, Sum(mp.amount) as TotalPayments
from employees e
left join monthly_payments mp on e.id = mp.employees_id
group by e.name
having Sum(mp.amount) > 100000;

-- **************************************************************
-- 7 statements with aggregate functions + group by without having.
-- find products in stocks with the highest price
select s.name, p.name, p.price as max_price
from products p
join stocks_has_products shp on shp.products_id = p.id
join stocks s on s.id = shp.stocks_id
where (p.price, shp.stocks_id) in (
		select Max(price), stocks_id
		from products
		join stocks_has_products on products.id = stocks_has_products.products_id
		group by stocks_id
		)
order by shp.stocks_id, max_price desc;

-- show number of invoices for each month
select Monthname(date_time) as month, Count(id) as total_orders
from invoices
group by month;

-- average monthly salary for each role
select r.name as role, Avg(c.salary) as average_salary
from contracts c
join employees e on c.employees_id = e.id
join roles_has_accounts rha on e.id = rha.accounts_id
join roles r on rha.roles_id = r.id
group by r.name
order by 1;

-- sum of bonuses for each employee
select e.name, e.surname, Sum(bp.amount) as total_bonus
from employees e
join monthly_payments mp on e.id = mp.employees_id
left join bonus_payments bp on mp.id = bp.monthly_payments_id
group by e.name, e.surname
order by 1;

-- number of service orders for each employee (even not mechanics) 
select e.name, e.surname, Count(so.id) as service_order_count
from employees e
left join service_orders so on e.id = so.employees_id
group by e.name, e.surname
order by 3;

-- number of service orders in each workshop
select w.name as workshop_name, Count(so.id) as service_order_count
from workshops w
left join service_orders so on w.id = so.workshop_id
group by w.name;

-- average value of invoices for each customer
select c.name as customer_name, Avg(i.total_price) as avg_invoice_value
from customers c
join service_orders so on c.id = so.customers_id
left join invoices i on so.invoices_id = i.id
group by c.name;