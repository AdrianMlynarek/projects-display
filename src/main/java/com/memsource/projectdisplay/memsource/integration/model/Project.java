package com.memsource.projectdisplay.memsource.integration.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project {

    private String name;
    private String status;
    private String sourceLang;
    private String[] targetLangs;
}
