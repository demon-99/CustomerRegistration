package com.example.User_Registration.dto;

import com.example.User_Registration.enums.Gender;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String email;
    private String name;
    private Gender gender;
    private String ipAddress;
    private String country;
}
