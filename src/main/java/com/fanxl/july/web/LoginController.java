package com.fanxl.july.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanxl2 on 2017/1/3.
 */
@Controller
public class LoginController {

    @GetMapping("/login1")
    public ModelAndView login1(){
        ModelAndView mv = new ModelAndView("user/login1");
        mv.addObject("dateVar", new Date(new java.util.Date().getTime()));
        mv.addObject("name", "这个是测试文字");
        mv.addObject("isGirl", true);
        mv.addObject("number", 200);

        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("android");
        list.add("html");
        list.add("mysql");
        mv.addObject("myList", list);

        Map<String, String> map = new HashMap<>();
        map.put("java", "你好java123");
        map.put("html", "你好html");
        map.put("mysql", "你好mysql");
        mv.addObject("map", map);
        return mv;
    }
}
