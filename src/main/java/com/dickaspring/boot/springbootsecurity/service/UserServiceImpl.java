/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.service;

import com.dickaspring.boot.springbootsecurity.entity.Role;
import com.dickaspring.boot.springbootsecurity.entity.User;
import com.dickaspring.boot.springbootsecurity.repository.RepositoryRoles;
import com.dickaspring.boot.springbootsecurity.repository.RepositoryUser;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private RepositoryUser repositoryUser;
    
    @Autowired
    private RepositoryRoles repositoryRoles;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public User findUserByEmail(String email) {
      return repositoryUser.findByEmail(email);
    }

    @Override
    public void createUser(User user) {
        //saved with encoded password dengan algoritma bycript encoded
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = repositoryRoles.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        repositoryUser.save(user);
    }
    
}
