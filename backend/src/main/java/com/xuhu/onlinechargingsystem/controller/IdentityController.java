package com.xuhu.onlinechargingsystem.controller;

import com.xuhu.onlinechargingsystem.domain.City;
import com.xuhu.onlinechargingsystem.domain.Customer;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@RestController
public class IdentityController {

    //inject database
    @Autowired
    private CustomerMapper customerMapper;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/login/signin")
    public String signIn(@RequestParam("username") String username,
                         @RequestParam("password") String password, HttpSession session){

        if(!StringUtils.isEmpty(username)){

            //remind to input a username
        }
        else{
            Customer customer = customerMapper.queryUserByUsername(username);
            if(customer==null){
                //remind user not exist
            }
            else{
                String customerPassword = customer.getPassword();
                if(customerPassword.equals(password)){
                    //redirect to personal center page
                }
                else{
                    //remind user password is wrong
                }
            }
        }
        return "index";
    }

    @PostMapping(value = "/login/signup")
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("city") City city){
        //initialize a new customer
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setCity(city);

        //store the customer to the database
        customerMapper.addUser(customer);

        //redirect to personal center page

        return "";
    }
}
