package ua.weatherex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
        //http://localhost:8080/greeting?name=oleh
    }

    @GetMapping
    public String  main(Map<String, Object> model) {
        model.put("some", "hello all frends");
        return "main";
        //http://localhost:8080/
    }
}
