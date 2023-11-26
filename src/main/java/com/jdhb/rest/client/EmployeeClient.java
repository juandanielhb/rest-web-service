package com.jdhb.rest.client;

import com.jdhb.rest.exception.EmployeeClientException;
import com.jdhb.rest.wsdl.Employee;
import com.jdhb.rest.wsdl.SaveEmployeeRequest;
import com.jdhb.rest.wsdl.SaveEmployeeResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.client.core.SoapActionCallback;


@Slf4j
public class EmployeeClient extends WebServiceGatewaySupport {

    @Value("${soap.service.namespace}")
    private String namespace;
    @Value("${soap.service.employees.url}")
    private String employeesUrl;

    public SaveEmployeeResponse saveEmployee(Employee employee) {

        SaveEmployeeRequest request = new SaveEmployeeRequest();
        request.setEmployee(employee);

        SoapActionCallback soapActionCallback = new SoapActionCallback(namespace + "/SaveEmployeeRequest");

        try {
            log.info("Request to : {}" , employeesUrl);
            SaveEmployeeResponse response = (SaveEmployeeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(employeesUrl, request, soapActionCallback);
            return response;
        } catch (SoapFaultClientException ex){
            log.error("Fault soap client : {}", ex.getMessage());
            throw new EmployeeClientException("Employee could not be saved", ex);
        }
    }
}
