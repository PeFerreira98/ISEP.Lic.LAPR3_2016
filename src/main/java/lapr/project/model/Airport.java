/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author João
 */
public class Airport {

    private String name;
    private String town;
    private String country;
    private String IATAcode;
    private Location location;

    public Airport(String name, String town, String country, String IATAcode, Location location) {
        this.name = name;
        this.town = town;
        this.country = country;
        this.IATAcode = IATAcode;
        this.location = location;
    }
    
    public Airport(String name, String town, String country, String IATAcode) {
        this.name = name;
        this.town = town;
        this.country = country;
        this.IATAcode = IATAcode;
    }

    public Airport(Airport air) {
        this(air.getName(), air.getTown(), air.getCountry(), air.getIATAcode(), 
                new Location(air.getLocation()));
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }

    public String getIATAcode() {
        return IATAcode;
    }
    
    public Location getLocation(){
        return this.location;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.IATAcode);
        return hash;
    }
    
    @Override
    public boolean equals(Object otherObj) {
            if (this == otherObj) {
                return true;
            }
            if (otherObj == null || this.getClass() != otherObj.getClass()) {
                return false;
            }
            Airport otherAirport = (Airport) otherObj;

            return this.IATAcode.equalsIgnoreCase(otherAirport.getIATAcode());
        }

    

    @Override
    public String toString() {
        return IATAcode +'(' + country + ')';
    }

}
