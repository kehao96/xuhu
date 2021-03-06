package com.xuhu.onlinechargingsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    private String username;
    private String password;
    private City city;
    private int balance;
    private String address;
    private String emailAddress;

}
