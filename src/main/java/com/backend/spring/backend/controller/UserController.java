package com.backend.spring.backend.controller;

import com.backend.spring.backend.model.User;
import com.backend.spring.backend.repository.UserRepository;
import com.backend.spring.backend.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseMessage<User>> loginUser(@RequestBody User user){
        ResponseMessage<User> message = new ResponseMessage<>();
        User foundUser = userRepository.findByLoginAndPassword(user.getLogin(),user.getPassword());
        if (foundUser==null){
            message.setSuccess(false);
            message.setMessage("Credential error");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else {
            message.setSuccess(true);
            message.setObject(foundUser);
            message.setMessage("ok");
            return ResponseEntity.ok(message);
        }
    }
}
