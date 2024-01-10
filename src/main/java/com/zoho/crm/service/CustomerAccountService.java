package com.zoho.crm.service;

import com.zoho.crm.responsedto.CustomerAccountDTO;
import org.springframework.http.ResponseEntity;

public interface CustomerAccountService {
    public ResponseEntity<?> create(CustomerAccountDTO customerAccountDTO);

    ResponseEntity viewByUsername(String userName);

    ResponseEntity deleteById(int id);

    ResponseEntity updateById(int id, CustomerAccountDTO customerAccountDTO);
}
