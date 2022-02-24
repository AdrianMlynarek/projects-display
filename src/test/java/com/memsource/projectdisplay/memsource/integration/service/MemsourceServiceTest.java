package com.memsource.projectdisplay.memsource.integration.service;

import com.memsource.projectdisplay.memsource.integration.exception.MemsourceLoginFailedException;
import com.memsource.projectdisplay.memsource.integration.model.Project;
import com.memsource.projectdisplay.memsource.integration.request.response.ProjectResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.memsource.projectdisplay.memsource.integration.MemsourceConsts.MEMSOURCE_LIST_PROJECTS_ENDPOINT;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class MemsourceServiceTest {
    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    private MemsourceConnector memsourceConnector;
    private MemsourceService memsourceService;

    @Test
    public void shouldReturnListOfProjects() {
        // given
        String mockedToken = "123";

        memsourceConnector = Mockito.mock(MemsourceConnector.class);
        memsourceService = new MemsourceService(memsourceConnector, restTemplate);

        when(memsourceConnector.getMemsourceApiToken()).thenReturn(mockedToken);

        String[] targetLangs = {"eng", "de"};
        Project project1 = new Project("FirstProject", "OPEN", "pl", targetLangs);
        Project project2 = new Project("SecondProject", "CLOSED", "fr", targetLangs);

        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);
        ProjectResponse projectResponse = new ProjectResponse(projectList);

        when(restTemplate.exchange(matches(MEMSOURCE_LIST_PROJECTS_ENDPOINT), eq(HttpMethod.GET), any(), eq(ProjectResponse.class)))
                .thenReturn(new ResponseEntity(projectResponse, HttpStatus.OK));

        // when
        List<Project> memsourceProjectsResponse = memsourceService.getMemsourceProjects();

        // then
        Assertions.assertThat(memsourceProjectsResponse).contains(project1);
        Assertions.assertThat(memsourceProjectsResponse).contains(project2);
        Assertions.assertThat(memsourceProjectsResponse.size()).isEqualTo(2);
    }

    @Test
    public void shouldThrowExceptionWhenSecurityTokenIsNotRegistered() {
        // given
        memsourceConnector = Mockito.spy(new MemsourceConnector(restTemplate));
        memsourceService = new MemsourceService(memsourceConnector, restTemplate);

        // then
        Assertions.assertThatExceptionOfType(MemsourceLoginFailedException.class)
                .isThrownBy(() -> memsourceService.getMemsourceProjects());
    }

}