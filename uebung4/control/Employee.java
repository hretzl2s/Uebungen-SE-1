package org.hbrs.se1.ws22.uebung4.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable, Comparable {
    // Serializable: Zur Laufzeit sind Objekte abspreicherbar

    private String vorname;
    private String name;
    private Integer pid;
    private String abteilung;
    private String rolle;

    private List<String> expertisen = new ArrayList<>();

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setRolle(String rolle) { this.rolle = rolle; }

    public String getRolle() { return rolle; }

    public List<String> getExpertisen() {
        return expertisen;
    }

    public void addExpertise(String expertise) {
        expertisen.add(expertise);
    }

    public void addExpertisenAsList(List<String> expertisen) {
        this.expertisen.addAll(expertisen);
    }
    @Override
    public int compareTo(Object o) {
        Employee e = (Employee) o;
        if (this.pid == e.pid) {
            return 1;
        }
        if (this.pid != e.pid) {
            return 0;
        }
        return -1;
    }
    @Override
    public String toString() {
        return "ID: " + this.pid + ", Vorname: " + this.vorname + ", Nachname: " + this.name + ", Abteilung: " + this.abteilung + ", Rolle: " + this.rolle;
    }

}

