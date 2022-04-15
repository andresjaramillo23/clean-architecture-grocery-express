package  edu.gatech.GroceryExpress.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  edu.gatech.GroceryExpress.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}