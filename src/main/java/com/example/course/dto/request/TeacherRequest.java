package com.example.course.dto.request;

import com.example.course.model.Address;
import com.example.course.model.BankAccount;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeacherRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be in mail format")
    private String email;

    private Address address;

    private BankAccount bankAccount;
}
