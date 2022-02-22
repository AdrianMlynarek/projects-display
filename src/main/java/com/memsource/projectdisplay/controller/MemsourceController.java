package com.memsource.projectdisplay.controller;

import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import com.memsource.projectdisplay.memsource.integration.service.MemsourceAccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemsourceController {

    private final MemsourceAccountService memsourceAccountService;

    @PostMapping("/login")
    public void loginToMemsource(@RequestBody MemsourceAccount memsourceAccount) {
        memsourceAccountService.loginToMemsourceAccount(memsourceAccount);
    }
}
