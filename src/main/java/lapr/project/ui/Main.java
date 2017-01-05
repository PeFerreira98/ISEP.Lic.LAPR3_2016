package lapr.project.ui;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import lapr.project.model.Aircraft;
import lapr.project.model.Airport;
import lapr.project.model.CalculatorExample;
import lapr.project.model.Physics;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        CalculatorExample calculatorExample = new CalculatorExample();
//        int value = calculatorExample.sum(3, 5);
//        LOGGER.log(Level.INFO, String.valueOf(value));

        new MainUI();

    }

    private void physicsTest() {
//        
//
//        AirNetwork air = new AirNetwork();
//
//        String name = "a";
//        String town = "b";
//        String country = "c";
//        String IATAcode = "d";
        //Airport b = new Airport(name, town, country, IATAcode, 0, 0, 0);

//        String name2 = "a";
//        String town2 = "c";
//        String country2 = "c";
//        String IATAcode2 = "c";
        //Airport c = new Airport(name2, town2, country2, IATAcode2, 0, 0, 0);
//        air.a.put(1, b);
//        air.a.put(2, c);
//        
//        for (Airport a : air.a.values()) {
//            if(air.a.containsKey(2))
//            System.out.println(a.toString());
//        }
        Project project = new Project(0, "proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");

        Aircraft a1 = new Aircraft(project.getAircraftModelRegister().getAircraftModel("777-200ER"), "a1", "desc", 1, 1, 1);

        double expResult = 0.0;
        AirNetwork an = project.getAirNetwork();

        Deque<Node> deque = new ArrayDeque<>();

//        for (Node node : an.getMapNodes().values()) {
//            deque.add(node);
//        }
        Map<Double, LinkedList<Node>> expected;

        System.out.println(a1);

        Map<Double, LinkedList<Node>> result = an.getFastestPath(an.getNetwork(), an.getNode("PT02"), an.getNode("ES02"), deque, a1);

//        Map<Double, LinkedList<Node>> expResult = expected;
        System.out.println(result);

//        double a=Physics.calculateSegmentDistance(a1, an.getMapSegment().get("PT03"));
//        double b=Physics.calculateSegmentDistance(a1, an.getMapSegment().get("PT04"));
//        System.out.println(a);
//        System.out.println(b);
//        double c=Physics.calculateSegmentDistanceInMiles(a);
//        double d=Physics.calculateSegmentDistanceInMiles(b);
//        System.out.println((c/0.85)+(d/0.85));
        double a = Physics.calculateSegmentDistance(a1, an.getMapSegment().get("PTLS02"));
        double b = Physics.calculateSegmentDistance(a1, an.getMapSegment().get("LSMD01"));
        System.out.println(a);
        System.out.println(b);

        System.out.println(result.entrySet().iterator().next().getValue());

//        System.out.println((a / Physics.speedAndMMOConverterMachToKmsHour(a1.getModel().getRegimeRegister().getRegime("cruise").getSpeed()))
//                + (b /Physics.speedAndMMOConverterMachToKmsHour(a1.getModel().getRegimeRegister().getRegime("cruise").getSpeed())));
    }
}
