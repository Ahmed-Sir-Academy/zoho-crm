package com.zoho.crm.service;

import com.zoho.crm.dao.LoginDao;
import com.zoho.crm.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Override
    public String login(String username, String password) {
        //Fetch the username and password from database by calling dao layer
        LoginEntity userNameAndPasswordFromDatabase = loginDao.validateUserNameAndPasswordFromDataBase(username, password);

        //Verify the username and password is correct or nor
        if (userNameAndPasswordFromDatabase != null) {
            if (userNameAndPasswordFromDatabase.getUserName().equals(username) && userNameAndPasswordFromDatabase.getPassword().equals(password)) {
                return "Login Successfully";
            }
        }else {
            return "The given username and password is not found in database";
        }
        return null;
    }
}
