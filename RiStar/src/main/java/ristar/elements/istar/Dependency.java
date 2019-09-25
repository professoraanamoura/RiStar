/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.elements.istar;

import java.util.UUID;

/**
 *
 * @author anamm
 */
public class Dependency {

    
    private UUID id;    
    private Dependum dependum;
    private DependableNode depender;
    private DependableNode dependee;
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Dependum getDependum() {
        return dependum;
    }

    public void setDependum(Dependum dependum) {
        this.dependum = dependum;
    }
    
    

    public DependableNode getDepender() {
        return depender;
    }

    public void setDepender(DependableNode depender) {        
        this.depender = depender;
    }

    public DependableNode getDependee() {
        return dependee;
    }

    public void setDependee(DependableNode dependee) {
        this.dependee = dependee;
    }
    
    
}
