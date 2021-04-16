package com.apilibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
