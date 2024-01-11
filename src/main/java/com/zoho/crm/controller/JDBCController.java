package com.zoho.crm.controller;

import com.zoho.crm.service.JDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jdbc")
public class JDBCController {

    @Autowired
    JDBCService jdbcService;

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchAllCustomerAccount(){
        return jdbcService.fetchAllCustomerAccount();
    }

}
