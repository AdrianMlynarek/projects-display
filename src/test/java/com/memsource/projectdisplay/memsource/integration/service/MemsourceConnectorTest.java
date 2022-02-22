package com.memsource.projectdisplay.memsource.integration.service;

import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import com.memsource.projectdisplay.memsource.integration.request.response.LoginResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static com.memsource.projectdisplay.memsource.integration.MemsourceConsts.MEMSOURCE_LOGIN_ENDPOINT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

class MemsourceConnectorTest {

    private MemsourceConnector memsourceConnector;

    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    @BeforeEach
    public void setup() {
        memsourceConnector = new MemsourceConnector(restTemplate);
    }

    @Test
    public void shouldAssignSecurityTokenOnSuccessfullyLogin() {
        // given
        String mockedToken = "123";
        LoginResponse successResponse = new LoginResponse();
        successResponse.setToken(mockedToken);

        when(restTemplate.exchange(matches(MEMSOURCE_LOGIN_ENDPOINT), eq(HttpMethod.POST), any(), eq(LoginResponse.class)))
                .thenReturn(new ResponseEntity(successResponse, HttpStatus.OK));

        // when
        memsourceConnector.loginToMemsource(new MemsourceAccount(123L, "user", "pwd"));

        // then
        Assertions.assertThat(memsourceConnector.getMemsourceApiToken()).isEqualTo(mockedToken);
    }
}