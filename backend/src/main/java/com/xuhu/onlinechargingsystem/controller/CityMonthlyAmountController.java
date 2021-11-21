package com.xuhu.onlinechargingsystem.controller;


import com.xuhu.onlinechargingsystem.domain.City;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;
import com.xuhu.onlinechargingsystem.util.SumElectricityAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CityMonthlyAmountController {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/city/{city}")
    public Map<Integer, Integer> citySearch(@PathVariable("city") String cityName){
        City city = City.valueOf(cityName);
        Map<Integer, Integer> map = SumElectricityAmount.cityElectricityMap(recordMapper,customerMapper,city);
        return map;
    }

}
