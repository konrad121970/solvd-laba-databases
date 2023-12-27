package com.solvd.laba;

import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.order.Vehicle;
import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.service.order.IServiceOrderService;
import com.solvd.laba.service.order.IVehicleService;
import com.solvd.laba.service.order.impl.ServiceOrderService;
import com.solvd.laba.service.order.impl.VehicleService;
import com.solvd.laba.service.people.IEmployeeService;
import com.solvd.laba.service.people.impl.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public static void main(String[] args) {

        IEmployeeService employeeService = new EmployeeService();
        IServiceOrderService serviceOrderService = new ServiceOrderService();
        IVehicleService vehicleService = new VehicleService();


        Employee employee = employeeService.getEmployeeById(1L);

        ServiceOrder serviceOrder = serviceOrderService.getServiceOrderById(1L);

        Vehicle vehicle = vehicleService.getVehicleById(1L);

        System.out.println("lol");

/*        Long employeeId = 1L;
        System.out.println("Monthly Payments with Bonuses for Employee ID " + employeeId + ":");
        monthlyPaymentService.getAllMonthlyPaymentsByEmployeeId(employeeId)
                .forEach(monthlyPayment -> {
                    System.out.println("\nMonthly Payment ID: " + monthlyPayment.getId()
                            + ", Amount: " + monthlyPayment.getAmount()
                            + ", Payment Date: " + monthlyPayment.getPaymentDate());

                    if (!monthlyPayment.getBonusPaymentList().isEmpty()) {
                        monthlyPayment.getBonusPaymentList().forEach(bonusPayment ->
                                System.out.println("  - Bonus Payment ID: " + bonusPayment.getId()
                                        + ", Amount: " + bonusPayment.getAmount()
                                        + ", Description: " + bonusPayment.getDescription()));
                    }
                });*/

    }
}