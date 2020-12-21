package com.cherry.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/2/20  5:02 PM
 * @Version
 **/
@Controller
@RequestMapping("/springboot")
public class WarController {


    @RequestMapping("/json")
    @ResponseBody
    public Object json(){
        Map map = new HashMap();
        map.put("id",1);
        map.put("name","john");
        return map;
    }


    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("city","上海");
        model.addAttribute("name","john");
        return "index";
    }
}
