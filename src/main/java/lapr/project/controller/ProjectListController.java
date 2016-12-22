/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.database.DatabaseModel;
import lapr.project.model.Project;
import lapr.project.ui.CopyProjectUI;
import lapr.project.ui.ProjectMenuUI;

/**
 *
 * @author Marcos
 */
public class ProjectListController {
    private List<Project> listProjects;

    public ProjectListController(){
        this.loadAllProjects();
    }

    public List<Project> getListProjects() {
        return listProjects;
    }

    public void setListProjects(List<Project> listProjects) {
        this.listProjects = listProjects;
    }

    /**
     * Carrega os Projects guardados.
     */
    public void loadAllProjects() {

        DatabaseModel db = new DatabaseModel();

        this.setListProjects(db.getProjects());

    }
    
    public void openProject(Project p){
        new ProjectMenuUI(p);
    }
    
    public void copyProject(Project proj){
        new CopyProjectUI(proj);
    }
}
