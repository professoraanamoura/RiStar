/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.elements.istar;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Objects;
import java.util.TreeSet;

/**
 *
 * @author anamm
 */
public class Model {
    private String name;
    private Set<Node> nodes = new TreeSet<Node>();

    public Set<Node> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }
    public Node getNode(String name){        
        Node node=null;
        for(Node n: nodes){
            if(n.getName().equalsIgnoreCase(name)) node=n;
        }
        return node;
    }
    public Node getNode(String name, Class type){        
        Node node=null;
        for(Node n: nodes){
            if(n.getName().equalsIgnoreCase(name) && n.getClass().equals(type)) node=n;
        }
        return node;
    }
    public void addNode(Node node){
        this.nodes.add(node);
    }
    public void removeNode(Node node){
        this.nodes.remove(node);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{" + "name=" + name + ", nodes=" + nodes + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final Model other = (Model) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
