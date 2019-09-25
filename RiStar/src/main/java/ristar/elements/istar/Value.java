/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.elements.istar;

import java.util.Objects;

/**
 *
 * @author anamm
 */
public class Value {
    private Object val;
    private Node node;
    private Property property;

    public Value(Object val, Node node, Property property) {
        this.val = val;
        this.node = node;
        this.property = property;
    }

    public Value() {
    }
    
    

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.node);
        hash = 29 * hash + Objects.hashCode(this.property);
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
        final Value other = (Value) obj;
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        if (!Objects.equals(this.property, other.property)) {
            return false;
        }
        return true;
    }
    
    
}
