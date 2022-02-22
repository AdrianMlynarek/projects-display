package com.memsource.projectdisplay.memsource.integration.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ListProjectsRequest {
    private final String userName;
    private final String password;
}
