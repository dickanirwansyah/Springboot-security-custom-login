/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.controller;

import com.dickaspring.boot.springbootsecurity.entity.User;
import com.dickaspring.boot.springbootsecurity.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author java-spring
 */
@Controller
public class ControllerLogin {
    
    @Autowired
    private UserService userService;
    
    //----page login----------//
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView getLoginPages(){
        ModelAndView view = new ModelAndView();
        view.setViewName("login/login");
        view.addObject("title", "Login Admin");
        return view;
    }
    
    
    //-----page registration-------//
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegiseration(){
        ModelAndView view = new ModelAndView();
        view.setViewName("registration/register");
        User user = new User();
        view.addObject("user", user);
        view.addObject("title", "Register User");
        return  view;
    }
    
    //-----proses registration---------//
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView getCreateRegistration(@Valid User user, BindingResult bindingResult){
        ModelAndView view = new ModelAndView();
        
        User ifUserExist = userService.findUserByEmail(user.getEmail());
        if(ifUserExist != null){
            bindingResult.rejectValue("email", "error.user", "Sorry email is already exist !");
        }
        if(bindingResult.hasErrors()){
            view.setViewName("registration/register");
        }else{
            userService.createUser(user);
            view.addObject("msg", "User has been created !");
            view.addObject("user", new User());
            view.setViewName("registration/register");
        }
        return view;
    }
    
    
    //-----page admin home or success direct-------//
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView getPageHome(){
        ModelAndView view = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User getUser = userService.findUserByEmail(auth.getName());
        view.addObject("title", "Status Login");
        view.addObject("name", "Nama : "+ getUser.getName());
        view.setViewName("home/pages");
        view.addObject("email", "Email : "+ getUser.getEmail());
        view.addObject("informasi", "Login Ini di protect dengan Spring Security & Csrf Token !");
        return view;
    }
    
    //------page 403------------//
    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView getAccessDenied(){
        ModelAndView view = new ModelAndView();
        view.setViewName("denied/403");
        view.addObject("title", "You are not authorized for the request data !");
        view.addObject("msg", "403");
        return view;
    }
}
