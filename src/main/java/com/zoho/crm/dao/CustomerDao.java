package com.zoho.crm.dao;

import com.zoho.crm.entity.CustomerAccountEntity;
import com.zoho.crm.responsedto.CustomerAccountDTO;

public interface CustomerDao {
    boolean createAccount(CustomerAccountDTO customerAccountDTO);
}
