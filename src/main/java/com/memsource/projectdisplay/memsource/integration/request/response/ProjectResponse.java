package com.memsource.projectdisplay.memsource.integration.request.response;

import com.memsource.projectdisplay.memsource.integration.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectResponse {
    private List<Project> content;
}
