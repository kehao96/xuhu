package com.xuhu.onlinechargingsystem.controller;

import com.xuhu.onlinechargingsystem.domain.Customer;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PaymentController {
    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/personalCenter")
    public String personalCenter(){
        return "pe";
    }

    @PostMapping(value = "/personalCenter/edit")
    public String editCustomerInformation(@RequestParam("address") String address,
                                          @RequestParam("name") String name,
                                          HttpSession session){
        String username = (String)session.getAttribute("username");
        Customer customer = customerMapper.queryUserByUsername(username);
        customer.setName(name);
        customer.setAddress(address);
        customerMapper.updateUser(customer);
        return "redirect:/personalCenter";
    }

    @PostMapping(value = "/personalCenter/payments")
    public String pay(){
        return "redirect:/personalCenter";
    }
}
