/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.repository;

import com.dickaspring.boot.springbootsecurity.entity.User;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author java-spring
 */
@Repository("repositoryUser")
public interface RepositoryUser extends JpaRepository<User, Long>{
    
    User findByEmail(String email);
}
