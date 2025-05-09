package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.model.issue.Issue;
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
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    public List<Issue> getIssues(String owner, String repo, Integer sinceIssues, Integer maxPages) {


        List<Issue> result = new ArrayList<Issue>();

        String since = ZonedDateTime.now(ZoneOffset.UTC)
                .minusDays(sinceIssues)
                .format(DateTimeFormatter.ISO_INSTANT);

        for (int i = 1; i <= maxPages; i++) {
            String uri = "https://api.github.com/repos/"+ owner + "/" + repo + "/issues" + "?since=" + since + "?page=" + i;
            Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
            assert issues != null;
            result.addAll(Arrays.stream(issues).toList());
        }
        return result;

    }
}
