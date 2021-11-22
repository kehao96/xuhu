package com.xuhu.onlinechargingsystem.util;

import com.xuhu.onlinechargingsystem.domain.City;
import com.xuhu.onlinechargingsystem.domain.PayRecord;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SumElectricityAmount {

    public static Map<Integer,Integer> cityElectricityMap(RecordMapper recordMapper,
                                                          CustomerMapper customerMapper,
                                                          City city){

        List<PayRecord> records = recordMapper.queryAllRecords();
        System.out.println(records.size());
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=1; i<=12; i++) {
            map.put(i,0);
        }
        for (PayRecord record: records) {
            City queryCity = customerMapper.queryUserByUsername(record.getUsername()).getCity();
            if(queryCity==city){
                int electricity = record.getElectricity();
                Date date = record.getDate();

                int month = date.getMonth()+1;
                System.out.println(month);
                int previousAmount =  map.get(month);
                map.put(month,previousAmount+electricity);
            }
        }
        return map;
    }
}
