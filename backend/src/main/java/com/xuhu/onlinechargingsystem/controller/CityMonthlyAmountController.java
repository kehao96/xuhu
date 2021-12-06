package com.xuhu.onlinechargingsystem.controller;


import com.xuhu.onlinechargingsystem.domain.City;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import com.xuhu.onlinechargingsystem.mapper.RecordMapper;
import com.xuhu.onlinechargingsystem.mlAlgorithm.LRRegression;
import com.xuhu.onlinechargingsystem.util.SumElectricityAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    @GetMapping("/predictCity/{city}")
    public List<Map<Integer, Double>> predictCitySearch(@PathVariable("city") String cityName){
        List<Map<Integer, Double>> list = new LinkedList<>();
        cityName = cityName.toLowerCase();
        Map<Integer, Double> map = null;
        Map<Integer, Double> predictMap = null;

        try {
            map = LRRegression.getRealData(cityName);
            predictMap = LRRegression.getPredictData(cityName);
            list.add(map);
            list.add(predictMap);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @GetMapping("/getR/{city}")
    public double getR(@PathVariable("city") String cityName){

        cityName = cityName.toLowerCase();
        return LRRegression.calcR(cityName);
    }
}
