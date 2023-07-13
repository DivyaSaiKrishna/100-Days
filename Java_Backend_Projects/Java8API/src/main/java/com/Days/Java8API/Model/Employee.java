package com.Days.Java8API.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class Employee {

    private String firstName;
    private String lastname;
    private String gender;
    private String emailId;
    private Integer age;

}
