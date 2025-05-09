package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;

    public Project getProject(String owner, String repo) {

        String uri = "https://api.github.com/repos/"+ owner + "/" + repo;
        Project project = restTemplate.getForObject(uri, Project.class);
        assert project != null;
        return project;
    }

    public void createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages){

    Project project = getProject(owner, repo);


    }

}

