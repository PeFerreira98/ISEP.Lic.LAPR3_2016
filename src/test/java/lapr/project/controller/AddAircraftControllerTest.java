/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zero_
 */
public class AddAircraftControllerTest {
    
    public AddAircraftControllerTest() {
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

    /**
     * Test of addAircraft method, of class AddAircraftController.
     */
    @Test
    public void testAddAircraft() {
        System.out.println("addAircraft");
        String filePath = "inOutFiles/TestSet01_Aircraft.xml";
        Project expProject = new Project(0, "Project0","Description0");
        
        AddAircraftController instance = new AddAircraftController(expProject);
        instance.addAircraft(filePath);
        
        //TODO: assertequals
    }
    
}
