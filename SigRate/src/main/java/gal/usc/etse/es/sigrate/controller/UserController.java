package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gal.usc.etse.es.sigrate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private final UserService users;

    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAlUsers() {
        return ResponseEntity.ok(users.get());
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<UserDTO> getUSerDtoById(@PathVariable Long id) {
        return ResponseEntity.ok(users.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return ResponseEntity.ok(users.create(user));
    }
}
