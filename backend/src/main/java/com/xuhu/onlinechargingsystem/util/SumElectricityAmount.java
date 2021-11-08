package com.xuhu.onlinechargingsystem.util;

import com.xuhu.onlinechargingsystem.domain.City;
import com.xuhu.onlinechargingsystem.domain.PayRecord;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumElectricityAmount {
    public Map<City,Integer> cityElectricityMap(RecordMapper recordMapper, CustomerMapper customerMapper){
        Map<City,Integer> map = new HashMap<>();
//        List<PayRecord> records = recordMapper.queryAllRecords();
//        for (PayRecord record: records) {
//            int electricity = record.getElectricity();
//            String username = record.getUsername();
//            City city = customerMapper.queryUserByUsername(username).getCity();
//
//            int previousAmount = map.getOrDefault(city,0);
//            map.put(city,previousAmount+electricity);
//        }

        return map;
    }
}
