package by.steshko.LIb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AboutUsController {

    @GetMapping("/aboutUs")
    public String main(Map<String, Object> model){
        return "aboutUs";
    }
}
