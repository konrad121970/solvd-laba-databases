package com.solvd.laba.parsers.sax;


import com.solvd.laba.parsers.model.Account;
import com.solvd.laba.parsers.model.Employee;
import com.solvd.laba.parsers.model.contract.BonusPayment;
import com.solvd.laba.parsers.model.contract.Contract;
import com.solvd.laba.parsers.model.contract.MonthlyPayment;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLEmployeeParser extends DefaultHandler {
    private StringBuilder currentElementValue;
    private Employee employee;
    private Contract currentContract;
    private MonthlyPayment currentMonthlyPayment;
    private BonusPayment currentBonusPayment;
    private Account account;
    private List<Contract> contracts;
    private List<MonthlyPayment> monthlyPayments;
    private List<BonusPayment> bonusPayments;

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElementValue = new StringBuilder();

        switch (qName) {
            case "employee":
                employee = new Employee();
                break;
            case "contract":
                currentContract = new Contract();
                if (contracts == null) {
                    contracts = new ArrayList<>();
                }
                break;
            case "monthlyPayment":
                currentMonthlyPayment = new MonthlyPayment();
                if (monthlyPayments == null) {
                    monthlyPayments = new ArrayList<>();
                }
                break;
            case "bonusPayment":
                currentBonusPayment = new BonusPayment();
                if (bonusPayments == null) {
                    bonusPayments = new ArrayList<>();
                }
                break;
            case "account":
                account = new Account();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentElementValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String value = currentElementValue.toString().trim();

        switch (qName) {
            case "id":
                if (currentContract != null) {
                    currentContract.setId(Long.parseLong(value));
                } else if (currentMonthlyPayment != null) {
                    currentMonthlyPayment.setId(Long.parseLong(value));
                } else if (currentBonusPayment != null) {
                    currentBonusPayment.setId(Long.parseLong(value));
                } else if (account != null) {
                    account.setId(Long.parseLong(value));
                } else {
                    employee.setId(Long.parseLong(value));
                }
                break;
            case "name":
                employee.setName(value);
                break;
            case "surname":
                employee.setSurname(value);
                break;
            case "phoneNumber":
                employee.setPhoneNumber(value);
                break;
            case "position":
                employee.setPosition(value);
                break;
            case "startDate":
                currentContract.setStartDate(LocalDate.parse(value));
                break;
            case "endDate":
                currentContract.setEndDate(LocalDate.parse(value));
                break;
            case "type":
                currentContract.setType(value);
                break;
            case "salary":
                currentContract.setSalary(Double.parseDouble(value));
                break;
            case "active":
                currentContract.setActive(Boolean.parseBoolean(value));
                break;
            case "amount":
                if (currentMonthlyPayment != null) {
                    currentMonthlyPayment.setAmount(Double.parseDouble(value));
                } else if (currentBonusPayment != null) {
                    currentBonusPayment.setAmount(Double.parseDouble(value));
                }
                break;
            case "paymentDate":
                currentMonthlyPayment.setPaymentDate(LocalDate.parse(value));
                break;
            case "description":
                currentBonusPayment.setDescription(value);
                break;
            case "login":
                account.setLogin(value);
                break;
            case "password":
                account.setPassword(value);
                break;
            case "contract":
                contracts.add(currentContract);
                currentContract = null;
                break;
            case "monthlyPayment":
                monthlyPayments.add(currentMonthlyPayment);
                currentMonthlyPayment = null;
                break;
            case "bonusPayment":
                bonusPayments.add(currentBonusPayment);
                currentBonusPayment = null;
                break;
            case "contracts":
                employee.setContracts(contracts);
                contracts = null;
                break;
            case "monthlyPayments":
                employee.setMonthlyPayments(monthlyPayments);
                monthlyPayments = null;
                break;
            case "bonusPayments":
                currentMonthlyPayment.setBonusPaymentList(bonusPayments);
                bonusPayments = null;
                break;
            case "account":
                employee.setAccount(account);
                account = null;
                break;
        }
    }
}
