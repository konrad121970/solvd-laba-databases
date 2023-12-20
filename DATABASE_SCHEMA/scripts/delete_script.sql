-- delete all employees that don't have a contract
delete
from employees e
where not exists (
		select 1
		from contracts c
		where e.id = c.employees_id
		);

-- delete all adresses in Warsaw
delete
from adresses
where city = 'Warsaw';

-- delete customer, his car and service orders
delete customers, vehicles, service_orders
from customers
left join vehicles on customers.id = vehicles.id
left join service_orders on customers.id = service_orders.customers_id
where customers.name = 'Ava' and customers.surname = 'Clark';

-- delete one customer 
delete
from employees
where name = 'John' and surname = 'Doe';

-- delete stock with all its products
delete s, shp, p
from stocks s
join stocks_has_products shp on shp.stocks_id = s.id
left join products p on shp.products_id = p.id
where s.name = 'Warehouse';

-- delete all vehicles with VIN number starting from '1'
delete
from vehicles
where vin like '1%';

-- delete all service orders with no invoice assigned
delete
from service_orders
where invoices_id is null;

-- delete all products not associated with any stock
delete
from products
where not exists (
		select 1
		from stocks_has_products shp
		where shp.products_id = products.id
		);

-- delete all bonus payments with amount higher than 2000
delete
from bonus_payments
where amount > 2000;

-- delete all invoices older than 1 year
delete
from invoices
where date_time < NOW() - INTERVAL 1 YEAR;