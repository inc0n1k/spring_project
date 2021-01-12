package n1k.spring_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = "")
public class AuthController {

    @GetMapping(path = "/login")
    public String loginPage(Principal principal) {
        return principal != null ? "redirect:homepage" : "login";
    }

    @GetMapping(path = "/")
    public String loginPageNull(Principal principal) {
        return principal != null ? "redirect:homepage": "login";
    }
}
