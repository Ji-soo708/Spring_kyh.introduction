package hello2.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 어노테이션 필요
public class HelloController {
    // Static
    @GetMapping("hello") // 웹어플리케이션에서 hello라고 들어오면 아래 메소드를 호출
    public String hello(Model model) {
        // 데이터를 hello!! 라고 넘길게
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // MVC
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API
    // 템플릿과 큰 차이점은 뷰 내용이 없고 문자열이 그대로 내려감
    @GetMapping("hello-string")
    @ResponseBody // http body에 "hello " + name 이 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring" 요청한 클라이언트에게 그대로 내려감
    }

    // 중요한 API
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();     // 객체를 만듦
        // 커맨드+쉬프트 치면 한번에 됨
        hello.setName(name);
        return hello; // hello라는 객체를 넣어줌
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}