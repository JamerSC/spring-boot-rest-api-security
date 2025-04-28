package com.jamersc.springboot.restcrud.dao;

import com.jamersc.springboot.restcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //
}
