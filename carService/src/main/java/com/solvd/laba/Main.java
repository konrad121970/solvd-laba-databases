package com.solvd.laba;

import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.order.Vehicle;
import com.solvd.laba.domain.people.Customer;
import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.service.order.IInvoiceService;
import com.solvd.laba.service.order.IServiceOrderService;
import com.solvd.laba.service.order.IVehicleService;
import com.solvd.laba.service.order.impl.InvoiceService;
import com.solvd.laba.service.order.impl.ServiceOrderService;
import com.solvd.laba.service.order.impl.VehicleService;
import com.solvd.laba.service.people.ICustomerService;
import com.solvd.laba.service.people.IEmployeeService;
import com.solvd.laba.service.people.impl.CustomerService;
import com.solvd.laba.service.people.impl.EmployeeService;
import com.solvd.laba.service.stock.IProductService;
import com.solvd.laba.service.stock.impl.ProductService;
import com.solvd.laba.service.workshop.IWorkshopService;
import com.solvd.laba.service.workshop.impl.WorkshopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public static void main(String[] args) {

        IEmployeeService employeeService = new EmployeeService();
        IServiceOrderService serviceOrderService = new ServiceOrderService();
        IVehicleService vehicleService = new VehicleService();
        IInvoiceService invoiceService = new InvoiceService();
        IProductService productService = new ProductService();
        ICustomerService customerService = new CustomerService();
        IWorkshopService workshopService = new WorkshopService();


        Employee employee = employeeService.getEmployeeById(1L);

        ServiceOrder serviceOrder = serviceOrderService.getServiceOrderById(1L);

        Vehicle vehicle = vehicleService.getVehicleById(1L);

        Invoice invoice = invoiceService.getInvoiceById(1L);

        Product product = productService.getProductById(1L);

        Customer customer = customerService.getCustomerById(1L);

        Workshop workshop = workshopService.getWorkshopById(1L);

        System.out.println("lol");


    }
}