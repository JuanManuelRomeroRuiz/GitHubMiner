package aiss.PIntegracion.ProIntegracion.controller;

import java.util.List;

import javax.validation.Valid;

import aiss.PIntegracion.ProIntegracion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import aiss.PIntegracion.ProIntegracion.model.Project;

@RestController
@RequestMapping("/GitHubMiner")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @GetMapping("/projects/{owner}/{repoName}")
    public Project getProject(
            @PathVariable String owner,
            @PathVariable String repoName){
        return projectService.getProject(owner,repoName);
    }

    @PostMapping("/projects/{owner}/{repoName}")
    public void createProject(
            @PathVariable String owner,
            @PathVariable String repoName,
            @RequestParam(defaultValue = "2") Integer sinceCommits,
            @RequestParam(defaultValue = "2") Integer sinceIssues,
            @RequestParam(defaultValue = "2") Integer maxPages) {
        projectService.createProject(owner,repoName,sinceCommits,sinceIssues,maxPages);
    }


}