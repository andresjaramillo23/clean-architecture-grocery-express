package  edu.gatech.GroceryExpress.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.gatech.GroceryExpress.models.ERole;
import edu.gatech.GroceryExpress.models.Role;
import edu.gatech.GroceryExpress.models.User;


import edu.gatech.GroceryExpress.repository.RoleRepository;
import edu.gatech.GroceryExpress.repository.UserRepository;

@RestController

public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser() {

        User user = new User("User1",
                "user@gmail.com",
                "password");
        userRepository.save(user);
        return ResponseEntity.ok("user registered");
    }

}