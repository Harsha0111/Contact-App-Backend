package com.harsha.demo.repository;

import com.harsha.demo.model.Contact;
import com.harsha.demo.model.ContactUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactUserRepository extends JpaRepository<ContactUser, Integer> {
    Optional<ContactUser> findByName(String name);

}
