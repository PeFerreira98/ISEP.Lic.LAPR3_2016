/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Project;
import lapr.project.model.register.AircraftModelRegister;
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
public class AircraftStAXParser2Test {
    
    public AircraftStAXParser2Test() {
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
     * Test of XMLReader method, of class AircraftStAXParser2.
     */
    @Test
    public void testXMLReader() {
        System.out.println("XMLReader");
        String filePath = "inOutFiles/TestSet02_Aircraft.xml";
        
        Project expProject = new Project("Project0","Description0");
        AircraftStAXParser2 instance = new AircraftStAXParser2(expProject);
        
        AircraftModelRegister expResult = expProject.getAircraftModelRegister();
        AircraftModelRegister result = instance.XMLReader(filePath);
        
        assertEquals(expResult, result);
    }
    
}
