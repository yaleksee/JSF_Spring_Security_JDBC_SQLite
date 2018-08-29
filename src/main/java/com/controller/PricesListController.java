package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/j_spring_security_logout")
public class PricesListController {
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "/pages/";
    }
}
