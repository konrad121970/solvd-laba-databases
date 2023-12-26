update stocks_has_products
set    count = count + 5
where  stocks_id = (select id
                    from   stocks
                    where  name = 'Shop')
       and products_id = (select id
                          from   products
                          where  product_number = 'P002');

update roles
set    name = 'Lead Mechanic'
where  name = 'Mechanic';

update accounts
set    password = 'new_hashed_password'
where  employees_id = (select id
                       from   employees
                       where  surname = 'Doe');

update contracts
set    active = 0
where  employees_id = (select id
                       from   employees
                       where  surname = 'Doe');

update accounts
set    login = 'john_doe_123'
where  employees_id = (select id
                       from   employees
                       where  surname = 'Doe');

update workshops
set    name = 'Auto garage'
where  name = 'Pablo\'s Auto Repair';

update adresses
set    city = 'Warsaw',street = 'Main',postal_code = '20-123'
where  city = 'Bialystok';

update employees
set    name = 'Henry'
where  surname = 'Doe';

update employees
set    phone_number = '987654321'
where  surname = 'Doe'; 