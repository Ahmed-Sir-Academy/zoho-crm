package com.zoho.crm.dao;

import com.zoho.crm.entity.LoginEntity;

public interface LoginDao {

    LoginEntity validateUserNameAndPasswordFromDataBase(String username, String password);
}
