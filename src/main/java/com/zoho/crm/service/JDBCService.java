package com.zoho.crm.service;

import org.springframework.http.ResponseEntity;

public interface JDBCService {
    ResponseEntity<?> fetchAllCustomerAccount();
}
