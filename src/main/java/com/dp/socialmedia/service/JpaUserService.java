package com.dp.socialmedia.service;

import com.dp.socialmedia.entity.User;
import com.dp.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserService implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));

        log.info("User found in the database: {}", username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public User create(User user) {
        validateUserNotExist(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }


    @Override
    @Transactional
    public User update(User user) {
        var existing = findById(user.getId());
        validateUserNotExist(user);
        existing.setUsername(user.getUsername());
        existing.setPassword(user.getPassword());
        return repository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var user = findById(id);
        repository.delete(user);

    }

    private void validateUserNotExist(User user) {
        boolean alreadyExist = repository.findByUsername(user.getUsername()).isPresent();
        if (alreadyExist) {
            throw new RuntimeException();
        }
    }


}
