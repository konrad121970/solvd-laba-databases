package com.solvd.laba;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.order.Vehicle;
import com.solvd.laba.domain.people.Customer;
import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.service.contract.IBonusPaymentService;
import com.solvd.laba.service.contract.IContractService;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;
import com.solvd.laba.service.contract.impl.BonusPaymentService;
import com.solvd.laba.service.contract.impl.ContractService;
import com.solvd.laba.service.contract.impl.MonthlyPaymentService;
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
        IBonusPaymentService bonusPaymentService = new BonusPaymentService();
        IMonthlyPaymentsService monthlyPaymentsService = new MonthlyPaymentService();
        IContractService contractService = new ContractService();

        // **** MyBatis ****

//        BonusPayment bonusPayment = new BonusPayment();
//        bonusPayment.setId(1L);
//        bonusPayment.setDescription("UPDATE TEST");
//        bonusPayment.setAmount(1000D);
//        bonusPaymentService.updateBonusPayment(bonusPayment);
//
//        bonusPayment = bonusPaymentService.getBonusPaymentById(1L);
//
//        MonthlyPayment monthlyPayment = new MonthlyPayment();
//        monthlyPayment.setId(1L);
//        monthlyPayment.setPaymentDate(new Date(2023 - 1900, 0, 1));
//        monthlyPayment.setAmount(2500D);
//        monthlyPaymentsService.updateMonthlyPayment(monthlyPayment);
//
//        MonthlyPayment monthlyPayment1 = new MonthlyPayment();
//        monthlyPayment1.setPaymentDate(new Date(2023 - 1900, 0, 13));
//        monthlyPayment1.setAmount(5000D);
//        monthlyPaymentsService.createMonthlyPayment(monthlyPayment1, 1L);
//
//        Contract contract = new Contract();
//        contract.setActive(true);
//        contract.setStartDate(new Date(2023 - 1900, 0, 1));
//        contract.setEndDate(new Date(2023 - 1900, 0, 1));
//        contract.setType("Full-Time");
//        contract.setSalary(2000D);
//
//
//        Employee employee = new Employee();
//        employee.setName("konrad");
//        employee.setSurname("tee");
//        employee.setContracts(List.of(contract));
//        employee.setPhoneNumber("123123123");
//        employee.setPosition("Mechanic");


//        employeeService.createEmployee(employee, 1L);
//
//        monthlyPayment = monthlyPaymentsService.getMonthlyPaymentById(1L);

        Contract contract = contractService.getContractById(1L);

        String s = contract.getEndDate().toString();

        Employee employee = employeeService.getEmployeeById(1L);

        Workshop workshop = workshopService.getWorkshopById(1L);

        // **** JDBC ****

        ServiceOrder serviceOrder = serviceOrderService.getServiceOrderById(1L);

        Vehicle vehicle = vehicleService.getVehicleById(1L);

        Invoice invoice = invoiceService.getInvoiceById(1L);

        Product product = productService.getProductById(1L);
        

        Customer customer = customerService.getCustomerById(1L);


        System.out.println("test");


    }
}