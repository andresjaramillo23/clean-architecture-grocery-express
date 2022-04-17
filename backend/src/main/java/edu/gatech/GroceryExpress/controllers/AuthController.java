package  edu.gatech.GroceryExpress.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.gatech.GroceryExpress.models.ERole;
import edu.gatech.GroceryExpress.models.Role;
import edu.gatech.GroceryExpress.models.User;
import edu.gatech.GroceryExpress.repository.RoleRepository;
import edu.gatech.GroceryExpress.repository.UserRepository;
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