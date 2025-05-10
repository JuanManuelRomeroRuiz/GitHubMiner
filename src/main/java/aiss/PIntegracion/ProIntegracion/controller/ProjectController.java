package aiss.PIntegracion.ProIntegracion.controller;

import java.util.List;

import javax.validation.Valid;

import aiss.PIntegracion.ProIntegracion.model.target.Tproject;
import aiss.PIntegracion.ProIntegracion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import aiss.PIntegracion.ProIntegracion.model.Project;

@RestController
@RequestMapping("/github")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @GetMapping("/project/{owner}/{repoName}")
    public Project getProject(
            @PathVariable String owner,
            @PathVariable String repoName){
        return projectService.getProject(owner,repoName);
    }

    @GetMapping("/{owner}/{repoName}")
    public Tproject getProject(
            @PathVariable String owner,
            @PathVariable String repoName,
            @RequestParam(defaultValue = "2") Integer sinceCommits,
            @RequestParam(defaultValue = "2") Integer sinceIssues,
            @RequestParam(defaultValue = "2") Integer maxPages) {
        return projectService.getProject(owner,repoName,sinceCommits,sinceIssues,maxPages);
    }

    @PostMapping("/{owner}/{repoName}")
    public Tproject createProject(
            @PathVariable String owner,
            @PathVariable String repoName,
            @RequestParam(defaultValue = "2") Integer sinceCommits,
            @RequestParam(defaultValue = "2") Integer sinceIssues,
            @RequestParam(defaultValue = "2") Integer maxPages) {
        return projectService.createProject(owner,repoName,sinceCommits,sinceIssues,maxPages);
    }


}