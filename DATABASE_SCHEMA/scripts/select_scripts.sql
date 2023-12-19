USE solvd_database;

-- Select all service orders (also not completed)
SELECT so.date AS Service_order_date,
	   so.completed Service_order_completed,
       c.name AS Customer_name,
       c.surname AS Customer_surname,
       v.make AS Vehicle_make,
       v.model AS Vehicle_model,
       i.date_time AS Invoice_Date,
       i.total_price AS Invoice_Price,
       e.name AS Assigned_employee_name,
	   e.surname AS Assigned_employee_surname
FROM 
	service_orders so 
JOIN customers c ON so.customers_id = c.id
JOIN vehicles v ON so.vehicles_id = v.id
JOIN employees e ON so.employees_id = e.id
LEFT JOIN invoices i ON so.invoices_id = i.id;

-- Select all service orders (only completed)
SELECT so.date AS Service_order_date,
	   so.completed Service_order_completed,
       c.name AS Customer_name,
       c.surname AS Customer_surname,
       v.make AS Vehicle_make,
       v.model AS Vehicle_model,
       i.date_time AS Invoice_Date,
       i.total_price AS Invoice_Price,
       e.name AS Assigned_employee_name,
	   e.surname AS Assigned_employee_surname
FROM 
	service_orders so 
JOIN customers c ON so.customers_id = c.id
JOIN vehicles v ON so.vehicles_id = v.id
JOIN employees e ON so.employees_id = e.id
JOIN invoices i ON so.invoices_id = i.id;

-- List vehicles that were serviced by customers
SELECT so.id AS order_id, so.date, c.name AS customer_name, v.make AS vehicle_make
FROM service_orders so
INNER JOIN customers c ON so.customers_id = c.id
INNER JOIN vehicles v ON so.vehicles_id = v.id;

-- select all invoices that don't contain products
SELECT i.id AS Invoice_ID, i.total_price AS Price
FROM invoices i
LEFT JOIN invoices_has_products ihp ON i.id = ihp.invoices_id
WHERE ihp.invoices_id IS NULL;

-- select all products that heven't been assigned to an invoice
select p.id as product_id, p.name as product_name, p.price as product_price
from products p 
left join invoices_has_products ihp on p.id = ihp.products_id
where ihp.products_id IS NULL;

-- same as above but using right join
SELECT p.id as product_id, p.name as product_name, p.price as product_price
FROM invoices_has_products ihp
RIGHT JOIN products p ON p.id = ihp.products_id
WHERE ihp.products_id IS NULL;

-- *********************************************************
-- 7 statements with aggregate functions + group by + having:

-- show employees with count of assigned orders higher than 0
select e.id, e.name, e.surname, e.position, COUNT(so.id) as order_count
from employees e
left join service_orders so on e.id = so.employees_id
group by e.id
having order_count > 0
order by order_count DESC;

-- show how many products do workshops have in their stocks
select w.name as workshop, s.name as stock_name, COUNT(p.id) as product_count
from workshops w
join stocks s ON w.id = s.workshops_id
join stocks_has_products shp ON s.id = shp.stocks_id
join products p ON shp.products_id = p.id
group by w.name, s.name
having product_count > 0;

-- show average price of product in each stock when avg price is higher than 10
select s.name, avg(p.price) as average_product_price
from stocks s
join stocks_has_products shp on s.id = shp.stocks_id
join products p on p.id = shp.products_id
group by s.name
having average_product_price > 10;

-- show average bonus amount that is higher than 500
select bp.description, avg(bp.amount) as average_bonus
from bonus_payments bp
group by bp.description
having average_bonus > 500;

-- show value of products in each stock higher than 2000
select s.id as stock_id, s.name as stock_name, SUM(p.price * shp.count) as total_value
from stocks s
join stocks_has_products shp on s.id = shp.stocks_id
join products p on shp.products_id = p.id
group by s.id , s.name
having total_value > 2000;

-- number of invoices for each customer
SELECT c.name, COUNT(i.id) AS invoice_count
FROM customers c
JOIN service_orders so ON c.id = so.customers_id
left JOIN invoices i on i.id = so.invoices_id
GROUP BY c.name
HAVING COUNT(i.id) >= 0;

-- find employees that have been paid more than 100k 
SELECT e.name, SUM(mp.amount) AS TotalPayments
FROM employees e
LEFT JOIN monthly_payments mp ON e.id = mp.employees_id
GROUP BY e.name
HAVING SUM(mp.amount) > 100000;

-- **************************************************************
-- 7 statements with aggregate functions + group by without having.

-- find products in stocks with the highest price
SELECT s.name, p.name, p.price AS max_price
FROM products p
JOIN stocks_has_products shp ON shp.products_id = p.id
JOIN stocks s ON s.id = shp.stocks_id
WHERE (p.price, shp.stocks_id) IN (
    SELECT MAX(price), stocks_id
    FROM products
    JOIN stocks_has_products ON products.id = stocks_has_products.products_id
    GROUP BY stocks_id
)
ORDER BY shp.stocks_id, max_price DESC;

-- show number of invoices for each month
SELECT monthname(date_time) AS month, COUNT(id) AS total_orders
FROM invoices
GROUP BY month;
