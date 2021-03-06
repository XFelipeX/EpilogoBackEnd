package com.apilibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findAccountByCpf(@Param("cpf") String cpf);
	@Query("SELECT i from Account i where i.cep IS NOT NULL")
	Page<Account> findAccountByCep(Pageable pageable);
}
