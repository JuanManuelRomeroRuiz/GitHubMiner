package aiss.PIntegracion.ProIntegracion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aiss.PIntegracion.ProIntegracion.model.Commit;

@RestController
@RequestMapping("/api")
public class CommitController {

    // TODO: Uncomment after creating the repositories
//    @Autowired
//    ProjectRepository projectRepository;
//    @Autowired
//    CommitRepository commitRepository;

    // GET http://localhost:8080/api/projects/{projectId}/commits
    @GetMapping("/projects/{projectId}/commits")
    public List<Commit> getAllCommitsByProjectId(@PathVariable(value="projectId") long projectId) {
        // TODO: COMPLETE
        return null;
    }

    // GET http://localhost:8080/api/commits/{id}
    @GetMapping("/commits/{id}")
    public Commit findOne(@PathVariable(value="id") Long id) {
        // TODO: COMPLETE
        return null;
    }

    // POST http://localhost:8080/api/projects/{projectId}/commits
    @PostMapping("/projects/{projectId}/commits")
    @ResponseStatus(HttpStatus.CREATED)
    public Commit createCommit(@RequestBody @Valid Commit commit, @PathVariable("projectId") long projectId) {
        // TODO: COMPLETE
        return null;
    }

    // PUT http://localhost:8080/api/commits/{id}
    @PutMapping("/commits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCommit(@RequestBody @Valid Commit updatedCommit, @PathVariable Long id) {
        // TODO: COMPLETE
    }

    // DELETE http://localhost:8080/api/commits/{id}
    @DeleteMapping("/commits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommit(@PathVariable Long id) {
        // TODO: COMPLETE
    }

}