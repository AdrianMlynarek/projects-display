package com.memsource.projectdisplay.memsource.integration.service;

import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import com.memsource.projectdisplay.memsource.integration.repository.MemsourceAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemsourceAccountService {

    private final MemsourceAccountRepository memsourceAccountRepository;
    private final MemsourceConnector memsourceConnector;

    @Autowired
    public MemsourceAccountService (MemsourceAccountRepository memsourceAccountRepository, MemsourceConnector memsourceConnector) {
        this.memsourceAccountRepository = memsourceAccountRepository;
        this.memsourceConnector = memsourceConnector;
    }

    public void loginToMemsourceAccount(MemsourceAccount memsourceAccount) {
        memsourceAccountRepository.save(memsourceAccount);
        memsourceConnector.loginToMemsource(memsourceAccount);
    }
}
