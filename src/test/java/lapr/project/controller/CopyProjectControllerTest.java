/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago
 */
public class CopyProjectControllerTest {

    private final List<Project> listProjects;
    private final Project project;

    public CopyProjectControllerTest() {
        listProjects = new LinkedList<>();
        project = new Project("proj0", "proj");
        defaultProject();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private void defaultProject() {
       

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");

        this.listProjects.add(project);
        
    }

    /**
     * Test of createProject method, of class CopyProjectController.
     */
    @Test
    public void testCreateProject() {
        System.out.println("createProject");
        String name = "proj0";
        String description = "proj";
        CopyProjectController instance = new CopyProjectController(project);
        boolean expResult = true;
        boolean result = instance.createProject(name, description);
        assertEquals(expResult, result);

    }

    /**
     * Test of validateProjectNameAndDescription method, of class CopyProjectController.
     */
    @Test
    public void testValidateProjectNameAndDescription() {
        System.out.println("validateProjectNameAndDescription");
        String name = "proj0";
        String description = "proj";
        CopyProjectController instance = new CopyProjectController(project);
        boolean expResult = true;
        boolean result = instance.validateProjectNameAndDescription(name, description);
        assertEquals(expResult, result);
        
    }

    
}
