package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    public List<Commit> getCommits(String owner, String repo) {


        String uri = "https://api.github.com/repos/"+ owner + "/" + repo + "/commits";
        Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
        assert commits != null;
        return Arrays.stream(commits).toList();
    }
}
