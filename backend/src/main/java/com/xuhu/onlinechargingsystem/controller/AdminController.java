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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/adminCenter")
    public String login(HttpSession session){
        return "admin";
    }

    @GetMapping("/city")
    public String cityPage(){
        return "city";
    }

    @PostMapping(value = "/admin/search")
    public String searchUserRecords(@RequestParam("username") String username,
                                    HttpSession session){
        List<PayRecord> allRecords = recordMapper.queryAllRecordsByUsername(username);
        session.setAttribute("username",username);
        session.setAttribute("recordList",allRecords);
        return "redirect:/adminCenter";
    }

    @PostMapping(value = "/admin/addRecord")
    public String signIn(@RequestParam("addRecordName") String username,
                         @RequestParam("electricity") int electricity,
                         @RequestParam("date") String date,
                         RedirectAttributes redirectAttributes,
                         HttpSession session){

        Customer customer = customerMapper.queryUserByUsername(username);
        if(customer==null){
            redirectAttributes.addFlashAttribute("userNotExistMsg","No such user");
        }
        else{
            PayRecord record = new PayRecord();
            String city = customer.getCity().toString();
            CItyFee cItyFee = CItyFee.valueOf(city);
            record.setUsername(customer.getUsername());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                record.setDate(dateFormat.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
                return "redirect:/adminCenter";
            }
            record.setElectricity(electricity);
            record.setFee(electricity*cItyFee.getValue());
            record.setPayed(false);
            recordMapper.addRecord(record);

            session.setAttribute("username",username);
            session.setAttribute("email",customer.getEmailAddress());
            List<PayRecord> allRecords = recordMapper.queryAllRecordsByUsername(username);
            session.setAttribute("recordList",allRecords);
        }
        return "redirect:/adminCenter";
    }
}
