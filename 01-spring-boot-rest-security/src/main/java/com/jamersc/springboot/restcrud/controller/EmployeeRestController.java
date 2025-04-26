package com.jamersc.springboot.restcrud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamersc.springboot.restcrud.dto.EmployeeDTO;
import com.jamersc.springboot.restcrud.entity.Employee;
import com.jamersc.springboot.restcrud.exception.EmployeeIDNotAllowedInRequestBodyException;
import com.jamersc.springboot.restcrud.exception.EmployeeNotFoundException;
import com.jamersc.springboot.restcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // Previous Code: Refactor EmployeeDAO then user Employee Service
    // private final EmployeeDAO employeeDAO;

    @Autowired
    private final EmployeeService employeeService;

    // Previous Code: Refactor
    /*public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }*/

    @Autowired
    private final ObjectMapper objectMapper;

    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    // constructor injection


    // add mapping for GET /employees - find all employees
    @GetMapping("/employees")
    public List<EmployeeDTO> findAllEmployees() {
        //System.out.println(employeeService.findAll());
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId} - find employee by ID
    @GetMapping("/employees/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable int employeeId) {
        // find employee in db
        EmployeeDTO theEmployee = employeeService.findById(employeeId);

        // check if employee is not exist
        if (theEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        return theEmployee;
        // or
        //return employeeService.findById(employeeId);
    }

    // add mapping for POST /employees - add/create new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody EmployeeDTO theEmployee) {
        // save employee in the database
        return employeeService.save(theEmployee);
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody EmployeeDTO theEmployee) {
        return employeeService.save(theEmployee);
    }

    // add mapping for PATCH /employees/{employeeId} - patch employee... update partial
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> patchPayload) {
        // find the employee in the db
        EmployeeDTO tempEmployee = employeeService.findById(employeeId);

        // throw exception if null or not existing
        if (tempEmployee == null ) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        // throw exception if request body contains "id" key - not allowed to change the primary key
        if (patchPayload.containsKey("id")) {
            throw new EmployeeIDNotAllowedInRequestBodyException("Cannot change primary key to a value that already exists - " + employeeId);
        }

        // apply the patch payload to employee
        EmployeeDTO patchEmployee = apply(patchPayload, tempEmployee);

        // Employee dbEmployee = employeeService.save(patchEmployee);
        // return dbEmployee;

        // or inline return
        return employeeService.save(patchEmployee);

    }

    private EmployeeDTO apply(Map<String, Object> patchPayload, EmployeeDTO tempEmployee) {
        // convert employee object to JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class); // converted find object into JSON format

        // convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // merge patch updates into the employee node
        employeeNode.setAll(patchNode);

        // return - convert JSON object node back to Employee Object
        return objectMapper.convertValue(employeeNode, EmployeeDTO.class); // return inline
    }


    // add mapping for DELETE / employees - delete employee by id
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        // find the employee id
        EmployeeDTO tempEmployee = employeeService.findById(employeeId);

        // check if the employee is not null
        if (tempEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        // return message after deleting employee
        return "Deleted employee id - " + employeeId;
    }
}
