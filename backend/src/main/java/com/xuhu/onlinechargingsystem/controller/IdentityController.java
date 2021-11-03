package com.xuhu.onlinechargingsystem.controller;

import com.xuhu.onlinechargingsystem.domain.City;
import com.xuhu.onlinechargingsystem.domain.Customer;
import com.xuhu.onlinechargingsystem.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;


@Controller
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
                         @RequestParam("password") String password,
                         HttpSession session,
                         RedirectAttributes redirectAttributes){

        if(StringUtils.isEmpty(username)){

            //remind to input a username
            redirectAttributes.addFlashAttribute("msg","Please input your username");
            return "redirect:/login";
        }
        else{
            Customer customer = customerMapper.queryUserByUsername(username);
            if(customer==null){
                //remind user not exist
                redirectAttributes.addFlashAttribute("msg","This user doesn't exist");
                return "redirect:/login";
            }
            else{
                String customerPassword = customer.getPassword();
                if(customerPassword.equals(password)){
                    //redirect to personal center page
                    session.setAttribute("user",customer);
                    if(customer.getUsername().equals("admin")){
                        return "redirect:/adminCenter";
                    }
                    return "redirect:/personalCenter";
                }
                else{
                    //remind user password is wrong
                    redirectAttributes.addFlashAttribute("msg","Password is wrong");
                    return "redirect:/login";
                }
            }
        }
    }

    @PostMapping(value = "/login/signup")
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("repeatPassword") String repeatPassword,
                         @RequestParam("city") String cityName,
                         HttpSession session,
                         RedirectAttributes redirectAttributes){
        //initialize a new customer
        if(!repeatPassword.equals(password)){
            redirectAttributes.addFlashAttribute("msg","Your password doesn't match");
            return "redirect:/login";
        }
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        City city = City.valueOf(cityName.toUpperCase());
        customer.setCity(city);

        //store the customer to the database
        customerMapper.addUser(customer);
        session.setAttribute("user",customer);
        return "redirect:/personalCenter";
    }
}
