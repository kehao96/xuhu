package com.xuhu.onlinechargingsystem.mapper;

import com.xuhu.onlinechargingsystem.domain.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerMapper {
    Customer queryUserByUsername(String username);
    boolean addUser(Customer customer);
}
