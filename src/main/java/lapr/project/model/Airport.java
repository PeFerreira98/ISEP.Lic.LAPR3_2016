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
        return "Airport{" + "name=" + name + ", town=" + town + ", country=" + country + ", IATAcode=" + IATAcode + ", latitude=" + location.getLatitude() + ", longitude=" + location.getLongitude() + ", altitude=" + location.getAltitude() + '}';
    }

}
