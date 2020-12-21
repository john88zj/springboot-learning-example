package com.cherry.springboot.controller;

import com.cherry.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/2/24  5:40 PM
 * @Version
 **/
@Controller
@RequestMapping(value = "/springboot")
public class ThymeleafController {


    @RequestMapping(value = "/thymeleaf/index")
    public String index(Model model, HttpServletRequest request){
        model.addAttribute("data","springboot Thymeleaf");
        model.addAttribute("date",new Date());
        model.addAttribute("dateStr","2020-03-18 15:45:54");

        User user = new User();
        user.setId(1);
        user.setName("john");
        user.setPhone("1234");
//        user.setSex(1);
        model.addAttribute("user",user);


        User user2 = new User();
        user2.setId(3);
        user2.setName("jime");
        user2.setPhone("2222");

        model.addAttribute("user",user);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);


        model.addAttribute("userList",userList);

        request.getSession().setAttribute("people","jime");

        return "index";
    }

    @RequestMapping(value = "/thymeleaf/info")
    public String info(Model model,int id){
//        model.addAttribute("data","springboot Thymeleaf");
//
        User user = new User();
        user.setId(id);
        user.setName("john");
        user.setPhone("1234");
        model.addAttribute("user",user);

        return "info";
    }
}
