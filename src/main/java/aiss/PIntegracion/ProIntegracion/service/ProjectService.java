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

        String uri = "https://api.github.com/repos/"+ owner + "/" + repo;
        Project project = restTemplate.getForObject(uri, Project.class);
        assert project != null;
        return project;
    }

    public Tproject getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages){

        Tproject target = new Tproject();

        List<Tcommit> tcommits = new ArrayList<>();
        List<Tissue> tissues = new ArrayList<>();


        Project project = getProject(owner, repo);
        List<Commit> commits = commitService.getCommits(owner, repo, sinceCommits, maxPages);
        List<Issue> issues = issueService.getIssues(owner, repo, sinceIssues, maxPages);



        target.setId(project.getId().toString());
        target.setName(project.getName());
        target.setWebUrl(project.getUrl());

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

        target.setCommits(tcommits);

        for (Issue issue : issues) {
            Tissue tissue = new Tissue();

            tissue.setId(issue.getId().toString());
            tissue.setTitle(issue.getTitle());
            tissue.setDescription(issue.getBody());
            tissue.setState(issue.getState());
            tissue.setCreatedAt(tissue.getCreatedAt());
            tissue.setUpdatedAt(tissue.getUpdatedAt());
            tissue.setClosedAt(tissue.getClosedAt());

            List<String> l = new ArrayList<>();
            List<Label> lab = issue.getLabels();
            for (Label label : lab) {
                l.add(label.getName());
            }

            tissue.setLabels(l);
            tissue.setVotes(issue.getComments());

            Tuser tauthor = new Tuser();
            if (issue.getUser() != null) {

                tauthor.setId(issue.getUser().getId().toString());
                tauthor.setUsername(issue.getUser().getUsername());
                tauthor.setName(issue.getUser().getName());
                tauthor.setAvatarUrl(issue.getUser().getAvatarUrl());
                tauthor.setWebUrl(issue.getUser().getWeb_url());
            }

            tissue.setAuthor(tauthor);

            Tuser assignee = new Tuser();
            if (issue.getAssignee() != null) {

                assignee.setId(issue.getAssignee().getId().toString());
                assignee.setUsername(issue.getAssignee().getUsername());
                assignee.setName(issue.getAssignee().getName());
                assignee.setAvatarUrl(issue.getAssignee().getAvatarUrl());
                assignee.setWebUrl(issue.getAssignee().getWeb_url());
            }

            tissue.setAssignee(assignee);

            List<Comment> comments = commentService.getComments(issue.getCommentsUrl(),maxPages);
            List<Tcomment> tcomments = new ArrayList<>();

            for (Comment comment : comments) {

                Tcomment tcomment = new Tcomment();

                tcomment.setId(comment.getId().toString());
                tcomment.setBody(comment.getBody());
                tcomment.setCreatedAt(comment.getCreatedAt());
                tcomment.setUpdatedAt(comment.getUpdatedAt());

                Tuser author = new Tuser();

                if (comment.getAuthor() != null) {

                    author.setId(comment.getAuthor().getId().toString());
                    author.setUsername(comment.getAuthor().getUsername());
                    author.setName(comment.getAuthor().getName());
                    author.setAvatarUrl(comment.getAuthor().getAvatarUrl());
                    author.setWebUrl(comment.getAuthor().getWeb_url());
                }

                tcomment.setAuthor(author);

                tcomments.add(tcomment);
            }
            tissue.setComments(tcomments);
            tissues.add(tissue);
        }
        target.setIssues(tissues);

        return target;


    }

    public Tproject createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages){

        Tproject target = new Tproject();

        List<Tcommit> tcommits = new ArrayList<>();
        List<Tissue> tissues = new ArrayList<>();


        Project project = getProject(owner, repo);
        List<Commit> commits = commitService.getCommits(owner, repo, sinceCommits, maxPages);
        List<Issue> issues = issueService.getIssues(owner, repo, sinceIssues, maxPages);



        target.setId(project.getId().toString());
        target.setName(project.getName());
        target.setWebUrl(project.getUrl());

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

        target.setCommits(tcommits);

        for (Issue issue : issues) {
            Tissue tissue = new Tissue();

            tissue.setId(issue.getId().toString());
            tissue.setTitle(issue.getTitle());
            tissue.setDescription(issue.getBody());
            tissue.setState(issue.getState());
            tissue.setCreatedAt(tissue.getCreatedAt());
            tissue.setUpdatedAt(tissue.getUpdatedAt());
            tissue.setClosedAt(tissue.getClosedAt());

            List<String> l = new ArrayList<>();
            List<Label> lab = issue.getLabels();
            for (Label label : lab) {
                l.add(label.getName());
            }

            tissue.setLabels(l);
            tissue.setVotes(issue.getComments());

            Tuser tauthor = new Tuser();
            if (issue.getUser() != null) {

                tauthor.setId(issue.getUser().getId().toString());
                tauthor.setUsername(issue.getUser().getUsername());
                tauthor.setName(issue.getUser().getName());
                tauthor.setAvatarUrl(issue.getUser().getAvatarUrl());
                tauthor.setWebUrl(issue.getUser().getWeb_url());
            }

            tissue.setAuthor(tauthor);

            Tuser assignee = new Tuser();
            if (issue.getAssignee() != null) {

                assignee.setId(issue.getAssignee().getId().toString());
                assignee.setUsername(issue.getAssignee().getUsername());
                assignee.setName(issue.getAssignee().getName());
                assignee.setAvatarUrl(issue.getAssignee().getAvatarUrl());
                assignee.setWebUrl(issue.getAssignee().getWeb_url());
            }

            tissue.setAssignee(assignee);

            List<Comment> comments = commentService.getComments(issue.getCommentsUrl(),maxPages);
            List<Tcomment> tcomments = new ArrayList<>();

            for (Comment comment : comments) {

                Tcomment tcomment = new Tcomment();

                tcomment.setId(comment.getId().toString());
                tcomment.setBody(comment.getBody());
                tcomment.setCreatedAt(comment.getCreatedAt());
                tcomment.setUpdatedAt(comment.getUpdatedAt());

                Tuser author = new Tuser();

                if (comment.getAuthor() != null) {

                    author.setId(comment.getAuthor().getId().toString());
                    author.setUsername(comment.getAuthor().getUsername());
                    author.setName(comment.getAuthor().getName());
                    author.setAvatarUrl(comment.getAuthor().getAvatarUrl());
                    author.setWebUrl(comment.getAuthor().getWeb_url());
                }

                tcomment.setAuthor(author);

                tcomments.add(tcomment);
            }
            tissue.setComments(tcomments);
            tissues.add(tissue);
        }
        target.setIssues(tissues);

        restTemplate.postForEntity("http://localhost:8080/gitminer/projects", target, Tproject.class);

        return target;


    }

}

// restTemplate.postForEntity("http://localhost:8080/videominer/channels", youtubeChannelData, Void.class);