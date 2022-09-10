package com.harsha.demo.service;

import com.harsha.demo.exception.ResourceAlreadyExistException;
import com.harsha.demo.exception.ResourceNotFoundException;
import com.harsha.demo.model.Contact;
import com.harsha.demo.model.ContactUser;
import com.harsha.demo.repository.ContactRepository;
import com.harsha.demo.repository.ContactUserRepository;
import com.harsha.demo.request.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactUserRepository contactUserRepository;

    public List<Contact> getAll(Integer id) {
        ContactUser contactUser = getContactUser(id);
        return contactRepository
                .findByContactUser(contactUser);
    }

    private ContactUser getContactUser(Integer id) {
        return contactUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact does not exist"));
    }

    public List<Contact> add(ContactRequest contactRequest) {
        Optional<Contact> userExist = contactRepository.findByName(contactRequest.getName());
        if (userExist.isPresent()) {
            throw new ResourceAlreadyExistException("Contact already exists");
        } else {
            Contact contact = new Contact();
            contact.setName(contactRequest.getName());
            contact.setNumber(contactRequest.getNumber());
            contact.setContactUser(getContactUser(contactRequest.getUserId()));
            contact = contactRepository.save(contact);
        }

        return getAll(contactRequest.getUserId());
    }

    public List<Contact> update(ContactRequest contactRequest) {
        if (contactRequest != null && contactRequest.getId() != null) {
            Contact contact = new Contact();
            contact.setName(contactRequest.getName());
            contact.setNumber(contactRequest.getNumber());
            contact.setId(contactRequest.getId());
            contact.setContactUser(getContactUser(contactRequest.getUserId()));
            contact = contactRepository.save(contact);
        } else
            throw new ResourceNotFoundException("Contact does not exists");
        return getAll(contactRequest.getUserId());
    }

    public List<Contact> delete(Integer id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact does not exist"));
        contactRepository.delete(contact);
        return contactRepository.findByContactUser(contact.getContactUser());
    }

    public Contact getContactById(Integer id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact does not exist"));
    }
}
