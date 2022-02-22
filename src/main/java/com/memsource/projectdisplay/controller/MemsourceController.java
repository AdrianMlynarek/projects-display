package com.memsource.projectdisplay.controller;

import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import com.memsource.projectdisplay.memsource.integration.model.Project;
import com.memsource.projectdisplay.memsource.integration.service.MemsourceAccountService;
import com.memsource.projectdisplay.memsource.integration.service.MemsourceService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemsourceController {

    private final MemsourceAccountService memsourceAccountService;
    private final MemsourceService memsourceService;

    @PostMapping("/login")
    public void loginToMemsource(@RequestBody MemsourceAccount memsourceAccount) {
        memsourceAccountService.loginToMemsourceAccount(memsourceAccount);
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return memsourceService.getMemsourceProjects();
    }
}
