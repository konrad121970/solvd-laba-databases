package com.solvd.laba.parsers;


import com.solvd.laba.parsers.model.Employee;
import com.solvd.laba.parsers.sax.EmployeeParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ParsersMain {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        String xml = "src/main/resources/parsers/xml/employee.xml";

        EmployeeParser employeeParser = new EmployeeParser();

        saxParser.parse(new File(xml), employeeParser);

        Employee employee = employeeParser.getEmployee();

        System.out.println("lol");
    }
}
