package com.dailycodebuffer.springboottutorial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(path = "/")
    public String helloSpring() {
        return "Hellloo Ngan hue";
    }
}
