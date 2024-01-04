package com.zoho.crm.dao;

import com.zoho.crm.entity.LoginEntity;
import com.zoho.crm.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDaoImpl implements LoginDao {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public LoginEntity validateUserNameAndPasswordFromDataBase(String username, String password) {
        return loginRepository.findByUserNameAndPassword(username, password);
    }
}
