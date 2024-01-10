package com.zoho.crm.dao;

import com.zoho.crm.entity.CustomerAccountEntity;
import com.zoho.crm.repository.CustomerAccountRepository;
import com.zoho.crm.responsedto.CustomerAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    @Override
    public boolean findByUsername(String username) {
        Optional<CustomerAccountEntity> byUsername = accountRepository.findByUsername(username);
        return byUsername.isPresent();
    }

    @Override
    public CustomerAccountEntity findByUsernameForView(String userName) {
        Optional<CustomerAccountEntity> byUsername = accountRepository.findByUsername(userName);
        return byUsername.orElse(null);
    }

    @Override
    public boolean deleteById(int id) {
        Optional<CustomerAccountEntity> byId = accountRepository.findById(id);
        if (byId.isPresent()) {
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CustomerAccountEntity updateById(int id, CustomerAccountDTO customerAccountDTO) {
        Optional<CustomerAccountEntity> byId = accountRepository.findById(id);
        if (byId.isPresent()) {
            CustomerAccountEntity customerAccountEntity = byId.get();
            customerAccountEntity.setFirstName(customerAccountDTO.getFirstName());
            customerAccountEntity.setLastName(customerAccountDTO.getLastName());
            customerAccountEntity.setAge(customerAccountDTO.getAge());
            customerAccountEntity.setAddress(customerAccountDTO.getAddress());

            CustomerAccountEntity save = accountRepository.save(customerAccountEntity);
            return save;
        } else {
            return new CustomerAccountEntity();
        }
    }
}
