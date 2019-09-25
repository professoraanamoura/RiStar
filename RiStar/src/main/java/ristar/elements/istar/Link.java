/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.elements.istar;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author anamm
 */
public class Link implements Comparable<Link>{
    private UUID id;
    private Node from;
    private Node to;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    
    public Node getFrom() {
        return from;
    }

    private void setFrom(Node from) {
        this.from = from;        
    }

    public Node getTo() {
        return to;
    }

    private void setTo(Node to) {
        this.to = to;
    }

    public Link(Node from, Node to) {
        this.setFrom(from);
        this.setTo(to);
        from.setLink(this);
        //to.setLink(this);
    }
    
    public Link(){
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.from);
        hash = 59 * hash + Objects.hashCode(this.to);
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
        final Link other = (Link) obj;
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to, other.to)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Link l) {
        if (this.getFrom().compareTo(l.getFrom()) == 0)
                return this.getTo().compareTo(l.getTo());
        return  this.getFrom().compareTo(l.getFrom());
    }
}
