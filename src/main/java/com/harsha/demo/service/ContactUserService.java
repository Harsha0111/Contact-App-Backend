package com.harsha.demo.service;

import com.harsha.demo.exception.ResourceAlreadyExistException;
import com.harsha.demo.exception.ResourceNotFoundException;
import com.harsha.demo.model.ContactUser;
import com.harsha.demo.repository.ContactUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactUserService {

    @Autowired
    private ContactUserRepository contactUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ContactUser register(ContactUser contactUser) {
        contactUser.setPassword(bCryptPasswordEncoder.encode(contactUser.getPassword()));
        return contactUserRepository.save(contactUser);
    }

    public ContactUser login(ContactUser contactUser) {
        Optional<ContactUser> loggedInUser = contactUserRepository.findByName(contactUser.getName());

        if (loggedInUser.isPresent()) {
            System.out.println(!bCryptPasswordEncoder.matches(contactUser.getPassword(), loggedInUser.get().getPassword()));
            System.out.println(contactUser.getPassword());
            System.out.println(loggedInUser.get().getPassword());
            if (!bCryptPasswordEncoder.matches(contactUser.getPassword(), loggedInUser.get().getPassword()))
                throw new ResourceAlreadyExistException("Invalid password");
        } else {
            throw new ResourceAlreadyExistException("Invalid User name");
        }

        return loggedInUser.get();

    }
}
