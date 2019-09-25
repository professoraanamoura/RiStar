/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.elements.istar;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author anamm
 */
public class Actor extends DependableNode {

    private ActorType type;
    private Set<SRElement> srElements = new TreeSet<SRElement>();

    public ActorType getType() {
        return type;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String saida = "Actor{" + "type=" + type + " - " + getName() + "}:\n";
        for (SRElement element : srElements) {
            saida += element.toString() + "\n";
        }
        return saida;
    }

    public Set<SRElement> getSrElements() {
        return Collections.unmodifiableSet(srElements);
    }

    public void setSrElements(Set<SRElement> srElements) {
        this.srElements = srElements;
    }

    public void setSrElement(SRElement srElement) {
        this.srElements.add(srElement);
    }

    public Set<SRElement> getResources() {
        Set<SRElement> resources = new TreeSet<SRElement>();
        for (SRElement element : this.getSrElements()) {
            if (element.getType() == IntentionalType.Resource) {
                resources.add(element);
            }
        }
        return resources;
    }

    public SRElement getNode(String name) {
        SRElement node = null;
        for (SRElement n : srElements) {
            if (n.getName().equalsIgnoreCase(name)) {
                node = n;
            }
        }
        return node;
    }
}
