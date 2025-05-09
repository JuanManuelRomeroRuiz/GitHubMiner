package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.Comment;
import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getComments(String comment_url, Integer maxPages) {

        List<Comment> result = new ArrayList<Comment>();

        for (int i = 1; i <= maxPages; i++) {
            String uri = comment_url + "?page=" + i;
            Comment[] comments = restTemplate.getForObject(uri, Comment[].class);
            assert comments != null;
            result.addAll(Arrays.stream(comments).toList());
        }
        return result;
    }
}
