package com.speedroller.speed_roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EventsController {
    @GetMapping("/eventos")
    public String eventos() {
        return "events";
    }
}