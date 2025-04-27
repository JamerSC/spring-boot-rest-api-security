package com.jamersc.springboot.restcrud.service;

import com.jamersc.springboot.restcrud.dao.EmployeeRepository;
import com.jamersc.springboot.restcrud.dto.EmployeeDTO;
import com.jamersc.springboot.restcrud.entity.Employee;
import com.jamersc.springboot.restcrud.exception.EmployeeNotFoundException;
import com.jamersc.springboot.restcrud.mapper.EmployeeMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //  Integrate data from multiple sources (DAO/repositories)
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    // add employee repository
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeMapper.entitiesToDtos(employeeRepository.findAll());
    }

    /*@Override
    public EmployeeDTO findById(int theId) {
        Employee employee = employeeRepository.findById(theId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found."));
        return employeeMapper.entityToDto(employee);
    }*/
    // or
    @Override
    public EmployeeDTO findById(int theId) {
        // find the employee id using Optional - Java 8 features
        Optional<Employee> result = employeeRepository.findById(theId);
        // create an empty object
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new EmployeeNotFoundException("Employee ID not found - " + theId);
        }
        return employeeMapper.entityToDto(theEmployee);
    }

    @Override
    public Employee save(EmployeeDTO theEmployee) {
        Employee employeeDb = employeeMapper.dtoToEntity(theEmployee);

        // If the ID is 0, set it to null (to treat it as a new employee)
        if (employeeDb.getId() != null && employeeDb.getId() == 0) {
            employeeDb.setId(null);
        }

        // If the ID is not null, check if the employee exists before updating
        if (employeeDb.getId() != null) {
            employeeRepository.findById(Math.toIntExact(employeeDb.getId()))
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee id not found: " + employeeDb.getId()));
        }

        // Save (insert or update) the employee
        return employeeRepository.save(employeeDb);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
