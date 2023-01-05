package tr.com.swe599.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/")
    public String demo() {
        return "SWE599 k8s demo";
    }
}
