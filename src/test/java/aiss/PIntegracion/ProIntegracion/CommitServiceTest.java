package aiss.PIntegracion.ProIntegracion;

import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.service.CommitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CommitServiceTest {

    @Autowired
    private CommitService commitService;

    @Test
    public void testgetCommits() {

        List<Commit> commits = new ArrayList<>();
        commits = (List<Commit>) commitService.getCommits("spring-projects","spring-framework",2,2);
        System.out.println(commits);
    }
}
