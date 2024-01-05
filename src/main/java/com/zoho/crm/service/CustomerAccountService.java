package com.zoho.crm.service;

import com.zoho.crm.entity.CustomerAccountEntity;
import com.zoho.crm.responsedto.CustomerAccountDTO;
import org.springframework.http.ResponseEntity;

public interface CustomerAccountService {
    public ResponseEntity<?> create(CustomerAccountDTO customerAccountDTO);
}
