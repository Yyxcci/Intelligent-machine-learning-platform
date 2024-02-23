package com.yyx.aidemo1.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HelloController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String Hello() {
        return "Hello World!";
    }

}
