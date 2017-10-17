/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "role", 
        catalog = "belajar_security")
public class Role implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int idrole;
    
    @Column(name = "role")
    private String role;
    
    public int getIdrole(){
        return idrole;
    }
    
    public void setIdrole(int idrole){
        this.idrole = idrole;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
}
