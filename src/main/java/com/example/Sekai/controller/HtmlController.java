package com.example.Sekai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/code")
    public String code(){
        return "code";
    }

    @RequestMapping("/query")
    public String query(){
        return "query";
    }

}
