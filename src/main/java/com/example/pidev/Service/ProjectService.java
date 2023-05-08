package com.example.pidev.Service;

import com.example.pidev.Repository.ProjectRepository;
import com.example.pidev.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        project.setSkills(project.getSkills());
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            project.setId(id);
            return projectRepository.save(project);
        } else {
            return null;
        }
    }

    public boolean deleteProject(Long id) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            projectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
