package com.dataimport.repository;

import com.dataimport.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser, Integer> {
    CompanyUser findCompanyUserById(Integer id);
}
