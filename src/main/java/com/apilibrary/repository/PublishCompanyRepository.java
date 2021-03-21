package com.apilibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.PublishCompany;

@Repository
public interface PublishCompanyRepository extends JpaRepository<PublishCompany, Integer>{

}
