package com.mybatis.batis;

import com.mybatis.batis.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatisApplication {

			private Logger logger = LoggerFactory.getLogger(this.getClass());

			@Autowired
			private EmployeeRepository employeeRepository;

/*
			@Override
			public void run(String...args) throws Exception {

				logger.info("Inserting -> {}", employeeRepository.insert(new Employee(0L, "Ramesh", "Fadatare", "ramesh@gmail.com")));
				logger.info("Inserting -> {}", employeeRepository.insert(new Employee(0L, "John", "Cena", "john@gmail.com")));
				logger.info("Inserting -> {}", employeeRepository.insert(new Employee(10013L, "tony", "stark", "stark@gmail.com")));

				logger.info("Employee id 10011 -> {}", employeeRepository.findById(10011L));

				logger.info("Update 10003 -> {}", employeeRepository.update(new Employee(10011L, "ram", "Stark", "ramesh123@gmail.com")));

				employeeRepository.deleteById(10013L);

				logger.info("All users -> {}", employeeRepository.findAll());
				List<Employee> employees = employeeRepository.findAll();
				for (Employee emp: employees){
				//	System.out.println(employee.getFirstName() + employee.getLastName());
					logger.info(emp.getEmailId());
					logger.info(emp.getFirstName());
					logger.info(emp.getLastName());
					logger.info(String.valueOf(emp.getId()));
				}
			}
*/

			public static void main(String[] args) {
				SpringApplication.run(BatisApplication.class, args);
			}

}
