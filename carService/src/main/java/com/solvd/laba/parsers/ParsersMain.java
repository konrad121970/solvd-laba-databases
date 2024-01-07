package com.solvd.laba.parsers;


import com.solvd.laba.parsers.model.Employee;
import com.solvd.laba.parsers.sax.XMLEmployeeParser;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ParsersMain {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        String xmlFilePath = "src/main/resources/parsers/xml/employee.xml";
        String xsdFilePath = "src/main/resources/parsers/xml/employee.xsd";

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(new StreamSource(xsdFilePath));

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(xmlFilePath));

            LOGGER.info("XML validation success");

        } catch (SAXException e) {
            LOGGER.error("Validation failed: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("Error reading XML file: " + e.getMessage());
            e.printStackTrace();
        }


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLEmployeeParser XMLEmployeeParser = new XMLEmployeeParser();
        saxParser.parse(new File(xmlFilePath), XMLEmployeeParser);

        Employee saxEmployee = XMLEmployeeParser.getEmployee();

        // ***** JAXB *****
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employee jaxbEmployee = (Employee) unmarshaller.unmarshal(new File(xmlFilePath));
            System.out.println();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        // ***** Jackson *****


        System.out.println();
    }
}
