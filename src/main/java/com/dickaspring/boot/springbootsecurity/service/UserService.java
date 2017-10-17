/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.service;

import com.dickaspring.boot.springbootsecurity.entity.User;

/**
 *
 * @author java-spring
 */
public interface UserService {
    
    User findUserByEmail(String email);
    
    void createUser(User user);
}
