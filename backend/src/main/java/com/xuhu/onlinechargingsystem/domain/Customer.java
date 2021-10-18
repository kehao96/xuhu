package com.xuhu.onlinechargingsystem.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {
    private final String id;
    private final String name;
    private final String username;
    private final String password;
    private final City city;
}
