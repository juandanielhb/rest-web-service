package com.jdhb.rest.mapper;

import com.jdhb.rest.model.EmployeeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.GregorianCalendar;

import com.jdhb.rest.model.dto.EmployeeDTO;
import com.jdhb.rest.wsdl.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void mapEmployeeRequestToEmployee() throws DatatypeConfigurationException {
        String dateString = "16/09/1994";
        XMLGregorianCalendar xmlGregorianCalendar = stringToXmlGregorianCalendar(dateString, "dd/MM/yyyy");
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setId(1);
        employeeRequest.setName("John");
        employeeRequest.setSurname("Doe");
        employeeRequest.setDocumentType("ID");
        employeeRequest.setDocumentNumber("123456");
        employeeRequest.setBirthdate("16/09/1994");
        employeeRequest.setJoiningDate("16/09/1994");
        employeeRequest.setPosition("Developer");
        employeeRequest.setSalary(50000.0);

        Employee employee = employeeMapper.toEmployee(employeeRequest);

        assertNotNull(employee);
        assertEquals(BigInteger.valueOf(1L), employee.getId());
        assertEquals("John", employee.getName());
        assertEquals("Doe", employee.getSurname());
        assertEquals("ID", employee.getDocumentType());
        assertEquals("123456", employee.getDocumentNumber());
        assertEquals(xmlGregorianCalendar, employee.getJoiningDate());
        assertEquals(xmlGregorianCalendar, employee.getBirthdate());
        assertEquals("Developer", employee.getPosition());
        assertEquals(50000.0, employee.getSalary());
    }

    @Test
    void mapEmployeeDTOToEmployee() throws DatatypeConfigurationException {
        LocalDate localDate = LocalDate.now();
        Employee employee = new Employee();
        employee.setId(BigInteger.valueOf(1L));
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDocumentType("ID");
        employee.setDocumentNumber("123456");
        employee.setBirthdate(localDateToXmlGregorianCalendar(localDate));
        employee.setJoiningDate(localDateToXmlGregorianCalendar(localDate));
        employee.setPosition("Developer");
        employee.setSalary(50000.0);

        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);

        assertNotNull(employee);
        assertEquals(1, employeeDTO.getId());
        assertEquals("John", employeeDTO.getName());
        assertEquals("Doe", employeeDTO.getSurname());
        assertEquals("ID", employeeDTO.getDocumentType());
        assertEquals("123456", employeeDTO.getDocumentNumber());
        assertEquals(localDate, employeeDTO.getJoiningDate());
        assertEquals(localDate, employeeDTO.getBirthdate());
        assertEquals("Developer", employeeDTO.getPosition());
        assertEquals(50000.0, employeeDTO.getSalary());
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate )
            throws DatatypeConfigurationException {
        if ( localDate == null ) {
            return null;
        }

        return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth(),
                DatatypeConstants.FIELD_UNDEFINED );
    }

    private XMLGregorianCalendar stringToXmlGregorianCalendar( String date, String dateFormat ) {
        if ( date == null ) {
            return null;
        }

        try {
            if ( dateFormat != null ) {
                DateFormat df = new SimpleDateFormat( dateFormat );
                GregorianCalendar c = new GregorianCalendar();
                c.setTime( df.parse( date ) );
                return DatatypeFactory.newInstance().newXMLGregorianCalendar( c );
            }
            else {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar( date );
            }
        }
        catch (ParseException | DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

}
