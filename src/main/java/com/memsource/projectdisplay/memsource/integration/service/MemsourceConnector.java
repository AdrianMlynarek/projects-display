package com.memsource.projectdisplay.memsource.integration.service;

import com.memsource.projectdisplay.memsource.integration.config.MemsourceAccount;
import com.memsource.projectdisplay.memsource.integration.exception.MemsourceLoginFailedException;
import com.memsource.projectdisplay.memsource.integration.request.LoginRequest;
import com.memsource.projectdisplay.memsource.integration.request.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.memsource.projectdisplay.memsource.integration.MemsourceConsts.MEMSOURCE_LOGIN_ENDPOINT;

@Service
@Slf4j
public class MemsourceConnector {

    private String memsourceApiToken;

    private final RestTemplate restTemplate;

    @Autowired
    public MemsourceConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        restTemplate.setMessageConverters(getJsonMessageConverters());
        memsourceApiToken = null;
    }

    public void loginToMemsource(MemsourceAccount memsourceAccount) {
        memsourceApiToken = acquireSecurityToken(memsourceAccount);
    }

    public String getMemsourceApiToken() {
        if (memsourceApiToken != null) {
            return memsourceApiToken;
        }
        log.error("Memsource account not configured, can't obtain security token!");
        throw new MemsourceLoginFailedException("Memsource account not configured, use /login endpoint first!");
    }

    private String acquireSecurityToken(MemsourceAccount memsourceAccount) {
        log.info("Acquiring Memsource security token");
        HttpEntity<LoginRequest> loginRequestPayload = buildMemsourceLoginPayload(memsourceAccount);

        ResponseEntity<LoginResponse> loginResponse;
        try {
            loginResponse = restTemplate.exchange(MEMSOURCE_LOGIN_ENDPOINT, HttpMethod.POST, loginRequestPayload, LoginResponse.class);
            String memsourceToken = loginResponse.getBody().getToken();
            log.info("Memsource token successfully acquired");
            return memsourceToken;
        } catch (Exception e) {
            log.error("Failed to login to Memsource! Error:", e);
            throw new MemsourceLoginFailedException("Login to Memsource failed!", e);
        }
    }

    private HttpEntity<LoginRequest> buildMemsourceLoginPayload(MemsourceAccount memsourceAccount) {
        LoginRequest loginPayload = LoginRequest.builder()
                .userName(memsourceAccount.getUsername())
                .password(memsourceAccount.getPassword())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType((MediaType.APPLICATION_JSON));
        return new HttpEntity<>(loginPayload, headers);
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }
}
