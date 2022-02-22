package com.memsource.projectdisplay.memsource.integration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Project {

    private String name;
    private String status;
    private String sourceLang;
    private String[] targetLangs;
}
