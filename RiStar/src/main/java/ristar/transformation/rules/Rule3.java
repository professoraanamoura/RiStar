/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import java.util.UUID;
import ristar.elements.istar.Actor;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.Model;
import ristar.elements.istar.Property;
import ristar.elements.istar.SRElement;
import ristar.elements.istar.Value;

/**
 *
 * @author anamm
 */
public class Rule3 extends Rule {

    public Rule3() {
        super(null, null);
        setRuleNumber(3);
        setRuleDescription(" Transform attributes in Resources.");
    }

    public Rule3(Model model, CompilationUnit cu) {
        super(model, cu);
    }

    @Override
    public void visit(FieldDeclaration n, Void arg) {

        SRElement resource = new SRElement();
        resource.setId(UUID.randomUUID());
        resource.setType(IntentionalType.Resource);
        resource.setName(limparCaracteresInvalidos(n.getVariable(0).toString()));
        Property typeProperty = new Property("type");        
        Value value = new Value(n.getElementType(),resource,typeProperty);
        
        //identificando o No pai
        //identificando o No pai   
        String parentName = null;
        Node parentNode = n.getParentNode().get();
        while (!(parentNode instanceof ClassOrInterfaceDeclaration)) {
            parentNode = parentNode.getParentNode().get();
        }
        if (parentNode != null) {
            parentName = ((ClassOrInterfaceDeclaration) parentNode).getNameAsString();
        }
        Actor parent = (Actor) this.getModel().getNode(parentName);
        if (parent != null) {
            parent.setSrElement(resource);
        }
        super.visit(n, arg);
    }

    private String limparCaracteresInvalidos(String texto) {
        String novoTexto = texto;
        if(texto.contains("\"")){
            novoTexto = texto.replace("\"", "");
        }
        return novoTexto;
    }

}
