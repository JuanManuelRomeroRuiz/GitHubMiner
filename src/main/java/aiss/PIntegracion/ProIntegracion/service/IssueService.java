package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.model.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    public List<Issue> getIssues(String owner, String repo) {


        String uri = "https://api.github.com/repos/"+ owner + "/" + repo + "/issues";
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        assert issues != null;
        return Arrays.stream(issues).toList();
    }
}
