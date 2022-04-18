package  edu.gatech.GroceryExpress.controllers;

import edu.gatech.GroceryExpress.models.User;
import edu.gatech.GroceryExpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
@Controller
public class AuthController {

    @Autowired
    UserRepository repository;
    //    @GetMapping("/welcome")
//    public String viewHomePage() {
//        return "index";
//    }
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String createLoginForm(HttpServletResponse response){
        response.setHeader("Content-Type","text/html");
        return "welcome";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);

        return "register_success";
    }

//    public static void main(String a[]){
//
//        String password = "123456";
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(password);
//        System.out.print(encodedPassword);
//    }
}