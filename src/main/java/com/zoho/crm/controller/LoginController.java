package com.zoho.crm.controller;

import com.zoho.crm.responsedto.ResponseDTO;
import com.zoho.crm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String test() {
        return "Hi Login Successfully";
    }

    //Body -> Raw -> JSON == @RequestBody
    //Param == @RequestParam
    //Path == @RequestPath | @PathVariable

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        String response = "";

        ResponseDTO responseDTO = new ResponseDTO();

        //Simple Validation of username & Password
        if (username != null && !username.equals("") && password != null && !password.equals("")) {
            //Call Service Layer
            response = loginService.login(username, password);
        } else {
            response = "Username and password cannot be null or empty";
        }
        responseDTO.setResponse(response);
        return ResponseEntity.ok(responseDTO);
    }

}
