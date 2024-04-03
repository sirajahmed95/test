package com.mybatis.batis.serviceImpl;

import com.mybatis.batis.exception.ResourceNotFoundException;
import com.mybatis.batis.models.Employee;
import com.mybatis.batis.repository.EmployeeRepository;
import com.mybatis.batis.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final RedisTemplate<String, Employee> redisTemplate;


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        var key = "emp_" + id;
        final ValueOperations<String, Employee> operations = redisTemplate.opsForValue();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            final Employee post = operations.get(key);
            log.info("EmployeeServiceImpl.findEmployeeById() : cache post >> " + post.toString());
            return post;
        }
        final Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id));
        if(employee.isPresent()) {
            operations.set(key, employee.get(), 10, TimeUnit.SECONDS);
            log.info("EmployeeServiceImpl.findEmployeeById() : cache insert >> " + employee.get().toString());
            return employee.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Employee deleteById(long id) {
        final String key = "emp_" + id;
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("EmployeeServiceImpl.deletePost() : cache delete ID >> " + id);
        }
        final Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id));
        if(employee.isPresent()) {
         return  employeeRepository.deleteById(employee.get().getId());
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        final String key = "emp_" + employee.getId();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("EmployeeServiceImpl.updateEmployee() : cache delete >> " + employee.toString());
        }
        return employeeRepository.save(employee);
    }
}
