package com.healthcareapp.authenticationservice.services.implementations;

import com.healthcareapp.authenticationservice.entities.User;
import com.healthcareapp.authenticationservice.enums.Role;
import com.healthcareapp.authenticationservice.exceptions.RoleAssignException;
import com.healthcareapp.authenticationservice.models.RegisterRequestDTO;
import com.healthcareapp.authenticationservice.repositories.UserRepository;
import com.healthcareapp.authenticationservice.services.interfaces.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void addNewUser(RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        user.setUserId(registerRequestDTO.getUserId());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
        String id = registerRequestDTO.getUserId();
        Role role = id.contains("PAT") ? Role.PATIENT : (id.contains("DOC") ? Role.DOCTOR : null);
        if(role == null) {
            throw new RoleAssignException();
        }
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void addNewAdmin(RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        String randomUserId = "HMS_ADM_" + String.format("%03d", (int) (Math.random() * 1000));
        user.setUserId(randomUserId);
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Role getRole(String userId){
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getRole).orElse(null);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.get().getUserId(), user.get().getPassword(),
                true, true, true, true,
                roles);
    }
}

