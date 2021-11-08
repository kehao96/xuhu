package com.xuhu.onlinechargingsystem.controller;

import com.xuhu.onlinechargingsystem.domain.Customer;
import com.xuhu.onlinechargingsystem.domain.PayRecord;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {
    private static final int  PAGE_SIZE = 10;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/personalCenter")
    public String personalCenter(HttpSession session){
        Customer user = (Customer)session.getAttribute("user");
        System.out.println(user.getUsername());
        List<PayRecord> records = recordMapper.queryAllRecordsByUsername(user.getUsername());

        List<PayRecord> paidRecords = new ArrayList<>();
        List<PayRecord> unpaidRecords = new ArrayList<>();
        for (PayRecord record: records) {
            if(record.isPayed()){
                paidRecords.add(record);
            }
            else {
                unpaidRecords.add(record);
            }
        }
//        records = records.subList(0,PAGE_SIZE);
        //System.out.println(records.get(0).getElectricity());
        session.setAttribute("unpaidRecordList",unpaidRecords);
        session.setAttribute("paidRecordList",paidRecords);
        return "pe";
    }
    @PostMapping(value = "/personalCenter/pay")
    public String submitPayment(@RequestParam("date") String date,
                                HttpSession session){
        Customer customer = (Customer) session.getAttribute("user");
        System.out.println(date);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);

//        PayRecord record = recordMapper.queryOneRecord(customer.getUsername(),date);
//        record.setPayed(true);
        return "redirect:/personalCenter";
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


}
