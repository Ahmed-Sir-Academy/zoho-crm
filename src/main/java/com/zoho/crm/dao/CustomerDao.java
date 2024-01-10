package com.zoho.crm.dao;

import com.zoho.crm.entity.CustomerAccountEntity;
import com.zoho.crm.responsedto.CustomerAccountDTO;

public interface CustomerDao {
    boolean createAccount(CustomerAccountDTO customerAccountDTO);

    boolean findByUsername(String username);


    CustomerAccountEntity findByUsernameForView(String userName);

    boolean deleteById(int id);

    CustomerAccountEntity updateById(int id, CustomerAccountDTO customerAccountDTO);
}
