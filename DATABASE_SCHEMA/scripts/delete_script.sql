
-- delete all employees that don't have a contract
DELETE FROM employees e
WHERE NOT EXISTS (SELECT 1 FROM contracts c WHERE e.id = c.employees_id);

-- delete all adresses in Warsaw
DELETE FROM adresses WHERE city = 'Warsaw';

-- delete customer, his car and service orders
DELETE customers, vehicles, service_orders
FROM customers
LEFT JOIN vehicles ON customers.id = vehicles.id
LEFT JOIN service_orders ON customers.id = service_orders.customers_id
WHERE customers.name = 'Ava' AND customers.surname = 'Clark';

-- delete one customer 
DELETE FROM employees WHERE name = 'John' AND surname = 'Doe';


DELETE stocks, products
FROM stocks s
join stocks_has_products shp on shp.stocks_id = s.id
LEFT JOIN products p ON stocks.id = p.stock_id
WHERE stocks.name = 'Warehouse A';

