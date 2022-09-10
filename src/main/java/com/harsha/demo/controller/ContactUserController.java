package com.harsha.demo.controller;

import com.harsha.demo.model.ContactUser;
import com.harsha.demo.response.APIResponse;
import com.harsha.demo.service.ContactUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RequestMapping("/api/auth")
public class ContactUserController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ContactUserService contactUserService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody ContactUser contactUser) {

        System.out.println(contactUser.toString());
        ContactUser user = contactUserService.login(contactUser);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody ContactUser contactUser) {

        ContactUser user = contactUserService.register(contactUser);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
