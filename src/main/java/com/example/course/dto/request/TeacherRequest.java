package com.example.course.dto.request;

import com.example.course.model.Address;
import com.example.course.model.BankAccount;
import lombok.Data;

@Data
public class TeacherRequest {
    private String name;
    private String email;
    private Address address;
    private BankAccount bankAccount;
}
