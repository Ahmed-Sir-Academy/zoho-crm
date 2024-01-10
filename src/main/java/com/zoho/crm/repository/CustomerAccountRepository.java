package com.zoho.crm.repository;

import com.zoho.crm.entity.CustomerAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccountEntity, Integer> {

    Optional<CustomerAccountEntity> findByUsername(String username);
}
