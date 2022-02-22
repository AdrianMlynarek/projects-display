package com.memsource.projectdisplay.memsource.integration.request.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class LoginResponse {
    @JsonProperty("token")
    private String token;
}
