package com.memsource.projectdisplay.controller;

import com.memsource.projectdisplay.memsource.integration.model.Project;
import com.memsource.projectdisplay.memsource.integration.service.MemsourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectsController {

    private final MemsourceService memsourceService;

    public ProjectsController(MemsourceService memsourceService) {
        this.memsourceService = memsourceService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return memsourceService.getMemsourceProjects();
    }
}
