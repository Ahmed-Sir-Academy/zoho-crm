package com.zoho.crm.controller;

import com.zoho.crm.responsedto.CustomerAccountDTO;
import com.zoho.crm.service.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    final static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Value("my.name")
    private String myName;

    @Autowired
    CustomerAccountService customerAccountService;

    // CRUD  == CREATE READ UPDATE DELETE

    //create    http://localhost:8080/customer/create
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CustomerAccountDTO customerAccountDTO) {
        ResponseEntity customerAccountEntity = customerAccountService.create(customerAccountDTO);
        return customerAccountEntity;
    }

    //Update   http://localhost:8080/customer/update/4
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody CustomerAccountDTO customerAccountDTO) {
        ResponseEntity customerAccountView = customerAccountService.updateById(id, customerAccountDTO);
        return customerAccountView;
    }

    //Delete  http://localhost:8080/customer/delete/0
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> view(@PathVariable(name = "id") int id) {
        ResponseEntity customerAccountView = customerAccountService.deleteById(id);
        return customerAccountView;
    }

    //View    http://localhost:8080/customer/view/touheed25
    @GetMapping("view/{username}")
    public ResponseEntity<?> view(@PathVariable(name = "username") String userName) {
        log.info("Entered in controller layer");
        ResponseEntity customerAccountView = customerAccountService.viewByUsername(userName);
        log.info("Exit from controller layer");
        return customerAccountView;
    }

    @GetMapping("/error")
    public ResponseEntity<?> errorTesting() {
        int a = 10;
        int b = 0;
        try {
            int c = a / b;
            System.out.println(c);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return null;
    }

}
