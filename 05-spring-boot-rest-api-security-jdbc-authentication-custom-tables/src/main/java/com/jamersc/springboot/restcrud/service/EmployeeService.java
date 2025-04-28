package com.jamersc.springboot.restcrud.service;

import com.jamersc.springboot.restcrud.dto.EmployeeDTO;
import com.jamersc.springboot.restcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();
    EmployeeDTO findById(int theId);
    Employee save(EmployeeDTO theEmployee);
    void deleteById(int theId);
}
