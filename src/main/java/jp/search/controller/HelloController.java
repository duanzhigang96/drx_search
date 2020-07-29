package jp.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "index/index";
    }
}
