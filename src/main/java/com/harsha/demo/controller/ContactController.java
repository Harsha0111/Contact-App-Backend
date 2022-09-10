package com.harsha.demo.controller;

import com.harsha.demo.model.Contact;
import com.harsha.demo.model.ContactUser;
import com.harsha.demo.model.Role;
import com.harsha.demo.request.ContactRequest;
import com.harsha.demo.response.APIResponse;
import com.harsha.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RequestMapping("/api/contact")
//@Secured({Role.ROLE_USER})
public class ContactController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<APIResponse> add(@RequestBody ContactRequest contactRequest) {

        List<Contact> contacts = contactService.add(contactRequest);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(contacts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody ContactRequest contactRequest) {
        List<Contact> contacts = contactService.update(contactRequest);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(contacts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<APIResponse> getAll(@PathVariable Integer id) {

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(contactService.getAll(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getContactById(@PathVariable Integer id) {

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(contactService.getContactById(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id) {

        List<Contact> contacts = contactService.delete(id);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(contacts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
