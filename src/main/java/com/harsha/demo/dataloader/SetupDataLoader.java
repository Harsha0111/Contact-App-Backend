package com.harsha.demo.dataloader;


import com.harsha.demo.exception.ResourceNotFoundException;
import com.harsha.demo.model.ContactUser;
import com.harsha.demo.model.Role;
import com.harsha.demo.repository.ContactUserRepository;
import com.harsha.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private ContactUserRepository contactUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//         Create user roles
        Role patientRole = createRoleIfNotFound(Role.USER);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private ContactUser createUserIfNotFound(final String name, final Role role) {
        ContactUser user = contactUserRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        if (user == null) {
            user = new ContactUser(name, bCryptPasswordEncoder.encode("admin"));
            user.setRoles(Set.of(role));
            user = contactUserRepository.save(user);
        }
        return user;
    }
}
