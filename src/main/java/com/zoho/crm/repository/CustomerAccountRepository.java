package com.zoho.crm.repository;

import com.zoho.crm.entity.CustomerAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccountEntity, Integer> {

}
