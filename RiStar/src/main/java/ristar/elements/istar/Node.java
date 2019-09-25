/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.elements.istar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 *
 * @author anamm
 */
public class Node implements Comparable<Node>{
    private UUID id;    
    private String name;
    private List<Value> properties;
    private Set<Link> links;
    

    Node(){
        links = new TreeSet<Link>();
        properties = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public Set<Link> getLinks() {
        return Collections.unmodifiableSet(links);
    }

    public void setLink(Link link) {
        this.links.add(link);
    }

    

    
    public List<Value> getProperties() {
        return Collections.unmodifiableList(properties);
    }
    public Value getProperty(String name) {
        for(Value value:properties){
            if(value.getProperty().getName().equalsIgnoreCase(name))
                return value;
        }
        return null;
    }

    public void setProperties(List<Value> properties) {
        this.properties = properties;
    }
    
    public void addProperty(Value property){
        this.properties.add(property);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    

    

    

    @Override
    public int compareTo(Node o) {
        return this.getName().compareTo(o.getName()); 
                
    }
    
    
    
    
}
