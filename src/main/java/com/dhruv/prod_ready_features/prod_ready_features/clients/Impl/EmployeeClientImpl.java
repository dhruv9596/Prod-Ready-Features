package com.dhruv.prod_ready_features.prod_ready_features.clients.Impl;

import com.dhruv.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.dhruv.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.dhruv.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.dhruv.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {
    
    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);


    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.trace("Trying to retrieve all employees : ");

        try{
            log.info("Attempting to call restClient Method in getAllEmployees.");
            ApiResponse<List<EmployeeDTO>> employeeDTOList =  restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , ((request, response) -> {
                        log.error("Response Error occured : " + new  String(response.getBody().readAllBytes()) );
                        throw new ResourceNotFoundException("Couldn't create the employee.");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });

            //Parameterized As we're receiving Lits
            //.body(new ParameterizedTypeReference<>() {})
            //If we only have single EmployeeDTO.class
            //.body(EmployeeDTO.class)

            log.info("Successfully return the employees in getAllEmployees " );

            log.trace("Retrieved emloyees list in getAllEmployees : {}, {}, {} : " , employeeDTOList.getData() , "Hello" , 5);

            return employeeDTOList.getData();

        } catch (Exception e) {
            log.error("Exception occured in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get employee by Id : {} " , employeeId );
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.get()
                    .uri("employees/{employeeId}" , employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , ((request, response) -> {
                        log.debug("4xxClient error occurred during createNewEmployee ");
                        log.error(new  String(response.getBody().readAllBytes()) );
                        throw new ResourceNotFoundException("Couldn't get the employee. ");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });
                log.trace("Successfully created a new employee : {}" , employeeDTOApiResponse.getBody());
                return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            log.error("Exception occured in get employee by Id {} " , employeeId , e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create employee. " );
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , ((request, response) -> {
                        log.error(new  String(response.getBody().readAllBytes()) );
                        throw new ResourceNotFoundException("Couldn't create the employee.");
                    }))
                    .toEntity(new ParameterizedTypeReference<>() {});

            System.out.println("Create a Employee Response Status Code: "+ employeeDTOApiResponse.getStatusCode());
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            log.error("Exception occured while creating employee ", e);
            throw new RuntimeException(e);
        }
    }


}
