/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.database;

import java.util.List;
import lapr.project.model.*;
import lapr.project.model.network.Segment;

/**
 *
 * @author Sofia
 */
public class TestDataBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Project p = testGetProjects();
        System.out.println();

    }

    /**
     * Metodo utilizado para testar o metodo getProjects.
     *
     * @return primeiro projecto
     */
    public static Project testGetProjects() {
        System.out.println("getProjects");
        DatabaseModel instance = new DatabaseModel();
        List<Project> result = instance.getProjects();
        for (Project project : result) {
            System.out.println(project.getName());
        }
        return result.get(0);
    }

    /**
     * Metodo utilizado para testar o metodo getSegments
     * 
     * @param p project 
     */
    public static void testGetSegments(Project p) {
        System.out.println("getSegments");
        DatabaseModel instance = new DatabaseModel(p);
        List<Segment> result = instance.getSegments();
        for (Segment s : result){
            System.out.println(s.toString());
        }
    }
    
    
}
