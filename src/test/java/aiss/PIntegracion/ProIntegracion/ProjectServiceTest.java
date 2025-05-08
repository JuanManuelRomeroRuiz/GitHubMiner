package aiss.PIntegracion.ProIntegracion;


import aiss.PIntegracion.ProIntegracion.model.Project;
import aiss.PIntegracion.ProIntegracion.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void testgetProject() {

        Project project = projectService.getProject("spring-projects","spring-framework");
        String p = project.toString();
        System.out.println(p);
    }
}
