package aiss.PIntegracion.ProIntegracion;

import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.model.issue.Issue;
import aiss.PIntegracion.ProIntegracion.service.CommitService;
import aiss.PIntegracion.ProIntegracion.service.IssueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class IssueServiceTest {

    @Autowired
    private IssueService issueService;

    @Test
    public void testgetIssues() {

        List<Issue> issues = new ArrayList<>();
        issues = (List<Issue>) issueService.getIssues("spring-projects","spring-framework", 2,2);
        System.out.println(issues);
    }
}
