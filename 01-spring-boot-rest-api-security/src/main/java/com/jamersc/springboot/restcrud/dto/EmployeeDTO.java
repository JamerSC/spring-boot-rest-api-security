package com.jamersc.springboot.restcrud.dto;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
