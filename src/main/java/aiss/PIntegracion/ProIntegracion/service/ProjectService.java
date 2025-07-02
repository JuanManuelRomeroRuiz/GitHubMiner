package aiss.PIntegracion.ProIntegracion.service;

import aiss.PIntegracion.ProIntegracion.model.Comment;
import aiss.PIntegracion.ProIntegracion.model.Project;
import aiss.PIntegracion.ProIntegracion.model.commit.Commit;
import aiss.PIntegracion.ProIntegracion.model.issue.Issue;
import aiss.PIntegracion.ProIntegracion.model.issue.Label;
import aiss.PIntegracion.ProIntegracion.model.target.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;

    public Project getProject(String owner, String repo) {
        String uri = "https://api.github.com/repos/" + owner + "/" + repo;
        Project project = restTemplate.getForObject(uri, Project.class);
        assert project != null;
        return project;
    }

    public Tproject getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages) {
        return buildProject(owner, repo, sinceCommits, sinceIssues, maxPages, false);
    }

    public Tproject createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages) {
        Tproject target = buildProject(owner, repo, sinceCommits, sinceIssues, maxPages, true);
        restTemplate.postForEntity("http://localhost:8080/gitminer/projects", target, Tproject.class);
        return target;
    }

    private Tproject buildProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages, boolean post) {
        Tproject target = new Tproject();

        Project project = getProject(owner, repo);
        target.setId(project.getId().toString());
        target.setName(project.getName());
        target.setWebUrl(project.getUrl());

        List<Commit> commits = commitService.getCommits(owner, repo, sinceCommits, maxPages);
        List<Issue> issues = issueService.getIssues(owner, repo, sinceIssues, maxPages);

        target.setCommits(mapCommits(commits));
        target.setIssues(mapIssues(issues, maxPages));

        return target;
    }

    private List<Tcommit> mapCommits(List<Commit> commits) {
        List<Tcommit> tcommits = new ArrayList<>();
        for (Commit commit : commits) {
            Tcommit tcommit = new Tcommit();
            tcommit.setId(commit.getSha());
            tcommit.setTitle("");
            tcommit.setMessage(commit.getCommit().getMessage());
            tcommit.setAuthorName(commit.getCommit().getAuthor().getName());
            tcommit.setAuthorEmail(commit.getCommit().getAuthor().getEmail());
            tcommit.setAuthoredDate(commit.getCommit().getAuthor().getDate());
            tcommit.setWebUrl(commit.getUrl());
            tcommits.add(tcommit);
        }
        return tcommits;
    }

    private List<Tissue> mapIssues(List<Issue> issues, Integer maxPages) {
        List<Tissue> tissues = new ArrayList<>();
        for (Issue issue : issues) {
            Tissue tissue = new Tissue();
            tissue.setId(issue.getId().toString());
            tissue.setTitle(issue.getTitle());
            tissue.setDescription(issue.getBody());
            tissue.setState(issue.getState());
            tissue.setCreatedAt(issue.getCreatedAt());
            tissue.setUpdatedAt(issue.getUpdatedAt());
            tissue.setClosedAt(issue.getClosedAt());

            List<String> labels = new ArrayList<>();
            for (Label label : issue.getLabels()) {
                labels.add(label.getName());
            }
            tissue.setLabels(labels);
            tissue.setVotes(issue.getComments());

            tissue.setAuthor(mapUser(issue.getUser()));
            tissue.setAssignee(mapUser(issue.getAssignee()));

            List<Comment> comments = commentService.getComments(issue.getCommentsUrl(), maxPages);
            List<Tcomment> tcomments = new ArrayList<>();
            for (Comment comment : comments) {
                Tcomment tcomment = new Tcomment();
                tcomment.setId(comment.getId().toString());
                tcomment.setBody(comment.getBody());
                tcomment.setCreatedAt(comment.getCreatedAt());
                tcomment.setUpdatedAt(comment.getUpdatedAt());
                tcomment.setAuthor(mapUser(comment.getAuthor()));
                tcomments.add(tcomment);
            }
            tissue.setComments(tcomments);

            tissues.add(tissue);
        }
        return tissues;
    }

    private Tuser mapUser(aiss.PIntegracion.ProIntegracion.model.User user) {
        if (user == null || user.getId() == null) return null;
        Tuser tuser = new Tuser();
        tuser.setId(user.getId().toString());
        tuser.setUsername(user.getUsername());
        tuser.setName(user.getName());
        tuser.setAvatarUrl(user.getAvatarUrl());
        tuser.setWebUrl(user.getWeb_url());
        return tuser;
    }
}
