package com.mybatis.batis.service;

import com.mybatis.batis.models.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployeeService {


    public List<Employee> findAll();

    public Employee findById(long id);

    public Employee deleteById(long id);

    public Employee create(Employee employee);

    public Employee update(Employee employee);

}
