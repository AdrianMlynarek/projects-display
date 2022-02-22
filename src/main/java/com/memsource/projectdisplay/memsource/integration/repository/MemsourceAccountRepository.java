package com.memsource.projectdisplay.memsource.integration.repository;

import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemsourceAccountRepository extends CrudRepository<MemsourceAccount, Long> {
    List<MemsourceAccount> findAll();
}
