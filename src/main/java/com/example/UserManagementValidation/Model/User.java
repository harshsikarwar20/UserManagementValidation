package com.example.UserManagementValidation.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String UserId;
    private String UserName;
    private String DOB;
    private String Email;
    private String Phone;
    private LocalDate Date;
    private Timestamp Time;
}