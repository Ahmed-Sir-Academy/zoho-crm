package com.zoho.crm.repository;

import com.zoho.crm.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
    LoginEntity findByUserNameAndPassword(String username, String password);
}
