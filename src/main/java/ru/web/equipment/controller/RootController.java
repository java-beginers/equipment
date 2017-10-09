package ru.web.equipment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {


    @RequestMapping("/")
    public String getRootPage() {
        return "index";
    }


    @RequestMapping("/index")
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }


    @RequestMapping("/index.html")
    public String getIndexPage2() {
        return "index";
    }
}
