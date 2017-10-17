/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "user", 
        catalog = "belajar_security")
public class User implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int iduser;
    
    @Column(name = "email", nullable = false)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an Email")
    private String email;
    
    @Column(name = "password", nullable = false)
    @Length(min = 5, message = "*Your password must have at least 5 caracters")
    @NotEmpty(message = "*Please provide your password")
    @Transient
    private String password;
    
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "*Please provide your name")
    private String name;
    
    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "*Please provide your last name")
    private String lastname;
    
    @Column(name = "active")
    private int active;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    public int getIduser(){
        return iduser;
    }
    
    public void setIduser(int iduser){
        this.iduser = iduser;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name= name;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    
    public int getActive(){
        return active;
    }
    
    public void setActive(int active){
        this.active = active;
    }
    
    public Set<Role> getRoles(){
        return roles;
    }
    
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
}
