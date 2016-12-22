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

/**
 *
 * @author zero_
 */
public class AddAircraftModelControllerTest {
    
    public AddAircraftModelControllerTest() {
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
     * Test of addAircraftModel method, of class AddAircraftModelController.
     */
    @Test
    public void testAddAircraftModel() {
        System.out.println("addAircraftModel");
        
        String filePath = "inOutFiles/TestSet01_Aircraft.xml";
        Project expProject = new Project("Project0", "Description");
        
        AddAircraftModelController instance = new AddAircraftModelController(expProject);
        instance.addAircraftModel(filePath);
        
        //TODO: assertequals
    }
    
}
