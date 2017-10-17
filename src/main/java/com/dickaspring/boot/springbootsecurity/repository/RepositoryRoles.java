/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.repository;

import com.dickaspring.boot.springbootsecurity.entity.Role;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author java-spring
 */
@Repository("repositoryRoles")
public interface RepositoryRoles extends JpaRepository<Role, Integer>{
    
    Role findByRole(String role);
    
}
