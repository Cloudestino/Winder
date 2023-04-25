package com.example.pidev.Controller;

import com.example.pidev.Service.ProjectService;
import com.example.pidev.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectController projectController;


    @GetMapping("/allProjects")
        public List<Project> getAllProjects() {
            return projectService.getAllProjects();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
            Optional<Project> project = projectService.getProjectById(id);
            return project.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping("/addProject")

        public ResponseEntity<Project> createProject(@RequestBody Project project) {
        System.out.println(project);
            Project createdProject = projectService.createProject(project);
            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
            Project updatedProject = projectService.updateProject(id, project);
            if (updatedProject != null) {
                return new ResponseEntity<>(updatedProject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
            boolean isDeleted = projectService.deleteProject(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
