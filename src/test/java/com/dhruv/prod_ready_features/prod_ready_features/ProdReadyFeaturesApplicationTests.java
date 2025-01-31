package com.dhruv.prod_ready_features.prod_ready_features;

import com.dhruv.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.dhruv.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(3)
	void getAllEmployees(){
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeById(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1628382L);
		System.out.println("Employee : " + employeeDTO);
	}

	@Test
	@Order(1)
	void createEmployee(){
		EmployeeDTO newEmployee = new EmployeeDTO( null , "Dhruv" , "dhruv@gmail.com" , 22 , "ADMIN" , 54000.0 , LocalDate.of(2023 , 12 , 1 ) , true) ;
		EmployeeDTO employeeDTO = employeeClient.createEmployee(newEmployee);
		System.out.println("Role : " + employeeDTO.getRole());
	}

}
