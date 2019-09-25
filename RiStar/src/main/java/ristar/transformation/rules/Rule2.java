/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.UUID;
import ristar.elements.istar.Actor;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.Model;
import ristar.elements.istar.SRElement;

/**
 *
 * @author anamm
 */
public class Rule2 extends Rule {

    public Rule2() {
        super(null, null);
        setRuleNumber(2);
        setRuleDescription(" Transform methods in Tasks.");
    }

    public Rule2(Model model, CompilationUnit cu) {
        super(model, cu);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {

        SRElement task = new SRElement();
        task.setId(UUID.randomUUID());
        task.setType(IntentionalType.Task);
        task.setName(n.getNameAsString());

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
            parent.setSrElement(task);
        }
        super.visit(n, arg);

    }

}
