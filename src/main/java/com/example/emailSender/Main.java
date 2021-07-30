package com.example.emailSender;

import com.example.emailSender.dtos.ProjectDto;
import com.example.emailSender.model.Project;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        project.setCostTotalMateril(100);
        project.setCostTotallogistica(200);

        ProjectDto projectDto = new ProjectDto();
        projectDto.setCostTotallogistica(project.getCostTotallogistica());
        projectDto.setCostTotalMateril(project.getCostTotalMateril());
        projectDto.setCostTotal(project.getCostTotalMateril()+project.getCostTotallogistica());
    }
}
