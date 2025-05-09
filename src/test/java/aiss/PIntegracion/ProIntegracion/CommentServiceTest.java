package aiss.PIntegracion.ProIntegracion;

import aiss.PIntegracion.ProIntegracion.model.Comment;
import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.service.CommentService;
import aiss.PIntegracion.ProIntegracion.service.CommitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testgetComments() {

        List<Comment> comments = new ArrayList<>();
        comments = (List<Comment>) commentService.getComments("https://api.github.com/repos/spring-projects/spring-framework/issues/34863/comments");
        System.out.println(comments);
    }
}
