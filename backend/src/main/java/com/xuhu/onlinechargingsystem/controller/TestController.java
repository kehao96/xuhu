package com.xuhu.onlinechargingsystem.controller;

import com.xuhu.onlinechargingsystem.domain.PayRecord;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/test")
    public String login(){
        return "test";
    }

    @RequestMapping("/test/signup")
    @ResponseBody
    public String test(){
        return "test";
    }

    @RequestMapping("/test/addRecord")
    public String addRecord(){
        PayRecord record = new PayRecord();
        record.setDate(new Date());
        record.setUsername("kehao");
        record.setElectricity(10);
        record.setFee(10);
        recordMapper.addRecord(record);
        return "index";
    }

    @RequestMapping("/test/showRecords")
    public String showRecords()
    {
        List<PayRecord> recordList = recordMapper.queryAllRecords();

        System.out.println(recordList.get(0).getDate().getMonth());
        return "index";
    }
}
