package com.pursue.corner.integrated.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/integrated/unified")
@RestController 
public class EntranceController {

    @RequestMapping("/entrance/{bean}")
    public String hello(@PathVariable String bean, @RequestParam String param) {
    	System.out.println(bean);
    	System.out.println(param);
        return "hello";
    }


}