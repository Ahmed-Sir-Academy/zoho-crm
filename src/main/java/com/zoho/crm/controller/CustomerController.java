package com.zoho.crm.controller;

import com.zoho.crm.responsedto.CustomerAccountDTO;
import com.zoho.crm.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    CustomerAccountService customerAccountService;


    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CustomerAccountDTO customerAccountDTO) {
        ResponseEntity customerAccountEntity = customerAccountService.create(customerAccountDTO);
        return customerAccountEntity;
    }

}
