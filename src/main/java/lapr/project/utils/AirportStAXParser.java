/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import lapr.project.model.Airport;
import lapr.project.model.Location;
import lapr.project.model.Project;

/**
 *
 * @author Pedro Ferreira
 */
public class AirportStAXParser {

    private static final Logger LOG = Logger.getLogger("AirportStAXParserLog");
        
    private Project c_project;
    private Airport c_airport;
    private Location c_location;
    
    //airport boolean
    private boolean b_nome;
    private boolean b_town;
    private boolean b_country;

    //location boolean
    private boolean b_latitude;
    private boolean b_longitude;
    private boolean b_altitude;
    
    //airport
    private String s_IATACode;
    private String s_nome;
    private String s_town;
    private String s_country;

    //location
    private double d_latitude;
    private double d_longitude;
    private double d_altitude;
    
    
    public AirportStAXParser(Project project) {
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

                        if (qName.equalsIgnoreCase("airport_list")) {
                            System.out.println("Start Element : airport_list");
                        }

                        if (qName.equalsIgnoreCase("airport")) {
                            System.out.println("Start Element : airport");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            s_IATACode = attributes.next().getValue();
                        }

                        if (qName.equalsIgnoreCase("name")) {
                            b_nome = true;
                        }

                        if (qName.equalsIgnoreCase("town")) {
                            b_town = true;
                        }

                        if (qName.equalsIgnoreCase("country")) {
                            b_country = true;
                        }

                        if (qName.equalsIgnoreCase("location")) {
                            System.out.println("Start Element : location");
                        }

                        if (qName.equalsIgnoreCase("latitude")) {
                            b_latitude = true;
                        }
                        
                        if (qName.equalsIgnoreCase("longitude")) {
                            b_longitude = true;
                        }
                        
                        if (qName.equalsIgnoreCase("altitude")) {
                            b_altitude = true;
                        }

                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (b_nome) {
                            System.out.println("\tname: " + characters.getData());
                            s_nome = characters.getData();
                            b_nome = false;
                        }

                        if (b_town) {
                            System.out.println("\ttown: " + characters.getData());
                            s_town = characters.getData();
                            b_town = false;
                        }

                        if (b_country) {
                            System.out.println("\tcountry: " + characters.getData());
                            s_country = characters.getData();
                            b_country = false;
                        }

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

                        if (b_altitude) {
                            System.out.println("\taltitude: " + characters.getData());
                            String s_altitude = characters.getData().replace(" m", "");
                            d_altitude = Double.parseDouble(s_altitude);
                            b_altitude = false;
                        }

                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String endName = endElement.getName().getLocalPart();

                        if (endName.equalsIgnoreCase("location")) {
                            c_location = new Location(d_latitude, d_longitude, d_altitude);
                            System.out.println("End Element : location" + "\n");
                        }

                        if (endName.equalsIgnoreCase("airport")) {
                            c_airport = new Airport(s_nome, s_town, s_country, s_IATACode, c_location);
                            this.c_project.addAirport(c_airport);
                            System.out.println("End Element : airport" + "\n");
                        }

                        if (endName.equalsIgnoreCase("airport_list")) {
                            System.out.println("End Element : airport_list" + "\n");
                        }

                        if (endName.equalsIgnoreCase("Network")) {
                            System.out.println("End Element : network" + "\n");
                        }

                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            LOG.log(Level.INFO, filePath, e);
        }
        return this.c_project;
    }

}
