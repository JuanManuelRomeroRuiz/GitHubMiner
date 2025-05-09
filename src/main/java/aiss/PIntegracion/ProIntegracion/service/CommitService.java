package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.Comment;
import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    public List<Commit> getCommits(String owner, String repo, Integer sinceCommits, Integer maxPages) {


        List<Commit> result = new ArrayList<Commit>();

        String since = ZonedDateTime.now(ZoneOffset.UTC)
                .minusDays(sinceCommits)
                .format(DateTimeFormatter.ISO_INSTANT);

        for (int i = 1; i <= maxPages; i++) {
            String uri = "https://api.github.com/repos/"+ owner + "/" + repo + "/commits" + "?since=" + since + "?page=" + i;
            Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
            assert commits != null;
            result.addAll(Arrays.stream(commits).toList());
        }
        return result;
    }
}
