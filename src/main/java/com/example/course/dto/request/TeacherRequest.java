package com.example.course.dto.request;

import com.example.course.dao.entity.Address;
import com.example.course.dao.entity.BankAccount;
import lombok.Data;

@Data
public class TeacherRequest {
    private String name;
    private String email;
    private Address address;
    private BankAccount bankAccount;
}
