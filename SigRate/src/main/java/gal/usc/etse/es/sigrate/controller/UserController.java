package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        //Codigo
    }

    @GetMapping("/{id}")
    public User getUSerById(@PathVariable Long id) {
        //Codigo
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        //Codigo
        return new User();
    }
}
