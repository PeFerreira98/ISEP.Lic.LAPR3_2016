/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import lapr.project.model.Location;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;

/**
 *
 * @author Pedro Ferreira
 */
public class NetworkStAXParser {
    
    private Project c_project;
    private AirNetwork c_airnetwork;
    private Node c_node;
    private Segment c_segment;
    
    //node boolean
    //location boolean
    private boolean b_latitude;
    private boolean b_longitude;

    //segment boolean
    private boolean b_startNode;
    private boolean b_endNode;
    private boolean b_direction;
    
    //wind boolean
    private boolean b_windIntensity;
    private boolean b_windDirection;
    
    //node
    private String s_nodeId;
   //location
    private double d_latitude;
    private double d_longitude;

    //segment
    private String s_segmentId;
    private String s_startNode;
    private String s_endNode;
    private String s_direction;
    
    //wind
    private double d_windIntensity;
    private double d_windDirection;
    
    
    public NetworkStAXParser(Project project) {
        this.c_project = project;
    }

    public Project XMLReader(String filePath) {

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("Network")) {
                            System.out.println("Start Element : Network");
                        }

                        if (qName.equalsIgnoreCase("node_list")) {
                            System.out.println("Start Element : node_list");
                        }

                        if (qName.equalsIgnoreCase("node")) {
                            System.out.println("Start Element : node");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            s_nodeId = attributes.next().getValue();
                        }

                        if (qName.equalsIgnoreCase("latitude")) {
                            b_latitude = true;
                        }
                        
                        if (qName.equalsIgnoreCase("longitude")) {
                            b_longitude = true;
                        }
                        
                        if (qName.equalsIgnoreCase("segment_list")) {
                            System.out.println("Start Element : segment_list");
                        }
                        
                        if (qName.equalsIgnoreCase("segment")) {
                            System.out.println("Start Element : segment");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            s_segmentId = attributes.next().getValue();
                        }

                        if (qName.equalsIgnoreCase("start_node")) {
                            b_startNode = true;
                        }
                        
                        if (qName.equalsIgnoreCase("end_node")) {
                            b_endNode = true;
                        }
                        
                        if (qName.equalsIgnoreCase("direction")) {
                            b_direction = true;
                        }
                        
                        if (qName.equalsIgnoreCase("wind")) {
                            System.out.println("Start Element : wind");
                        }
                        
                        if (qName.equalsIgnoreCase("wind_intensity")) {
                            b_windIntensity = true;
                        }
                        
                        if (qName.equalsIgnoreCase("wind_direction")) {
                            b_windDirection = true;
                        }
                        
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (b_latitude) {
                            System.out.println("\tlatitude: " + characters.getData());
                            d_latitude = Double.parseDouble(characters.getData());
                            b_latitude = false;
                        }

                        if (b_longitude) {
                            System.out.println("\tlongitude: " + characters.getData());
                            d_longitude = Double.parseDouble(characters.getData());
                            b_longitude = false;
                        }
                        
//                        if (b_altitude) {
//                            System.out.println("\taltitude: " + characters.getData());
//                            String s_altitude = characters.getData().replace(" m", "");
//                            d_altitude = Double.parseDouble(s_altitude);
//                            b_altitude = false;
//                        }

                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String endName = endElement.getName().getLocalPart();
                        
                        if (endName.equalsIgnoreCase("node")) {
                            c_node = new Node(s_nodeId, new Location(d_latitude, d_longitude, 0));
//                            this.c_airnetwork
                            System.out.println("End Element : node" + "\n");
                        }

                        if (endName.equalsIgnoreCase("node_list")) {
                            System.out.println("End Element : node_list" + "\n");
                        }
                        
                        if (endName.equalsIgnoreCase("segment_list")) {
                            System.out.println("End Element : segment_list" + "\n");
                        }

                        if (endName.equalsIgnoreCase("Network")) {
                            System.out.println("End Element : network" + "\n");
                        }

                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
        }
        return this.c_project;
    }

}
