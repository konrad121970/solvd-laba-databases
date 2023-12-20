
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

-- delete stock with all its products
DELETE s, shp, p
FROM stocks s
join stocks_has_products shp on shp.stocks_id = s.id
LEFT JOIN products p ON shp.products_id = p.id
WHERE s.name = 'Warehouse';

-- delete all vehicles with VIN number starting from '1'
DELETE FROM vehicles WHERE vin LIKE '1%';

-- delete all service orders with no invoice assigned
DELETE FROM service_orders WHERE invoices_id IS NULL;

-- delete all products not associated with any stock
DELETE FROM products
WHERE NOT EXISTS (SELECT 1 FROM stocks_has_products shp WHERE shp.products_id = products.id);

-- delete all bonus payments with amount higher than 2000
DELETE FROM bonus_payments WHERE amount > 2000;

-- delete all invoices older than 1 year
DELETE FROM invoices WHERE date_time < NOW() - INTERVAL 1 YEAR;



