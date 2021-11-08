package com.xuhu.onlinechargingsystem.controller;

import com.xuhu.onlinechargingsystem.domain.CItyFee;
import com.xuhu.onlinechargingsystem.domain.Customer;
import com.xuhu.onlinechargingsystem.domain.PayRecord;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class AdminController {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/adminCenter")
    public String login(){
        return "admin";
    }

    @PostMapping(value = "/admin/addRecord")
    public String signIn(@RequestParam("username") String username,
                         @RequestParam("electricity") int electricity,
                         @RequestParam("date") Date date,
                         RedirectAttributes redirectAttributes){

        Customer customer = customerMapper.queryUserByUsername(username);
        if(customer==null){
            redirectAttributes.addFlashAttribute("userNotExistMsg","No such user");
        }
        else{
            PayRecord record = new PayRecord();
            String city = customer.getCity().toString();
            CItyFee cItyFee = CItyFee.valueOf(city);
            record.setUsername(customer.getUsername());
            record.setDate(date);
            record.setElectricity(electricity);
            record.setFee(electricity*cItyFee.getValue());
            record.setPayed(false);
            recordMapper.addRecord(record);
        }
        return "redirect:/adminCenter";
    }
}
