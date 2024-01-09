package com.zoho.crm.dao;

import com.zoho.crm.entity.CustomerAccountEntity;
import com.zoho.crm.repository.CustomerAccountRepository;
import com.zoho.crm.responsedto.CustomerAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    CustomerAccountRepository accountRepository;

    @Override
    public boolean createAccount(CustomerAccountDTO customerAccountDTO) {
        CustomerAccountEntity customerAccountEntity = new CustomerAccountEntity();
        customerAccountEntity.setFirstName(customerAccountDTO.getFirstName());
        customerAccountEntity.setLastName(customerAccountDTO.getLastName());
        customerAccountEntity.setAge(customerAccountDTO.getAge());
        customerAccountEntity.setAddress(customerAccountDTO.getAddress());
        customerAccountEntity.setUsername(customerAccountDTO.getUsername());
        customerAccountEntity.setPassword(customerAccountDTO.getPassword());
        customerAccountEntity.setConfirmPassword(customerAccountDTO.getConfirmPassword());

        accountRepository.save(customerAccountEntity);
        return true;
    }
}
