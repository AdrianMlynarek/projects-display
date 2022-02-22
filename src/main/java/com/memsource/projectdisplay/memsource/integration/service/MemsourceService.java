package com.memsource.projectdisplay.memsource.integration.service;
import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import com.memsource.projectdisplay.memsource.integration.exception.MemsourceLoginFailedException;
import com.memsource.projectdisplay.memsource.integration.model.Project;
import com.memsource.projectdisplay.memsource.integration.repository.MemsourceAccountRepository;
import com.memsource.projectdisplay.memsource.integration.request.ListProjectsRequest;
import com.memsource.projectdisplay.memsource.integration.request.LoginRequest;
import com.memsource.projectdisplay.memsource.integration.request.response.LoginResponse;
import com.memsource.projectdisplay.memsource.integration.request.response.ProjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.memsource.projectdisplay.memsource.integration.MemsourceConsts.MEMSOURCE_LIST_PROJECTS_ENDPOINT;

@Service
@Slf4j
public class MemsourceService {

    private final MemsourceAccountRepository memsourceRepository;
    private final MemsourceConnector memsourceConnector;
    private final RestTemplate restTemplate;

    @Autowired
    public MemsourceService (MemsourceAccountRepository memsourceRepository,
                             MemsourceConnector memsourceConnector,
                             RestTemplate restTemplate) {
      this.memsourceRepository = memsourceRepository;
      this.memsourceConnector = memsourceConnector;
      this.restTemplate = restTemplate;
    }

    public List<Project> getMemsourceProjects() {
        HttpEntity<ListProjectsRequest> requestEntity = getListProjectsRequest();
        ResponseEntity<ProjectResponse> projectListResponse;
        projectListResponse = restTemplate.exchange(MEMSOURCE_LIST_PROJECTS_ENDPOINT, HttpMethod.GET, requestEntity, ProjectResponse.class);
        return projectListResponse.getBody().getContent();
    }

    private HttpEntity<ListProjectsRequest> getListProjectsRequest() {
        String memsourceToken = memsourceConnector.getMemsourceApiToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "adrianmlynarek@gmail.com");
        headers.add("Authorization", "ApiToken " + memsourceToken);

        return new HttpEntity<>(null, headers);
    }
}
