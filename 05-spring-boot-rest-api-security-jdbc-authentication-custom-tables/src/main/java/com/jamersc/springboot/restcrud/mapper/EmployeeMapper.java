package com.jamersc.springboot.restcrud.mapper;

import com.jamersc.springboot.restcrud.dto.EmployeeDTO;
import com.jamersc.springboot.restcrud.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // This makes it a Spring-managed bean
public interface EmployeeMapper {

    EmployeeDTO entityToDto(Employee employee);
    Employee dtoToEntity(EmployeeDTO dto);
    List<EmployeeDTO> entitiesToDtos(List<Employee> employees);
    List<Employee> dtosToEntities(List<EmployeeDTO> dtos);
}
