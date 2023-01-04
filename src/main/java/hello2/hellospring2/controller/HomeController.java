package hello2.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // localhost:8080 들어오면 이게 호출됨
    public String home() {
        return "home"; // templates/home.html 이 호출됨
    }
}
