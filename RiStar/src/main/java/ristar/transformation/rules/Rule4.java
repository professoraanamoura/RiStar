/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ristar.transformation.rules;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import java.util.UUID;
import ristar.elements.istar.Actor;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.MeansEnd;
import ristar.elements.istar.Model;
import ristar.elements.istar.SRElement;

/**
 *
 * @author anamm
 */
public class Rule4 extends Rule{
    
    public Rule4() {
        super(null, null);
        setRuleNumber(4);
        setRuleDescription(" Transform constructors in Goal associate with a Task.");
    }


    public Rule4(Model model, CompilationUnit cu) {
        super(model, cu);
    }
    
    
    @Override
        public void visit(ConstructorDeclaration n, Void arg) {

            SRElement task = new SRElement();
            task.setId(UUID.randomUUID());
            SRElement goal = new SRElement();
            goal.setId(UUID.randomUUID());
            task.setType(IntentionalType.Task);

            goal.setType(IntentionalType.Goal);
            StringBuffer parameters = new StringBuffer();
            for (Node parameter : n.getParameters()) {
                parameters.append(((Parameter) parameter).getNameAsString() + ",");
            }
            String goalName = n.getName()
                    + (parameters.length() > 0 ? " by " + parameters.toString() : "")
                    + " be accomplished. ";
            goal.setName(goalName);
            task.setName(n.getNameAsString());
            //criando o link
            MeansEnd meansEnd = new MeansEnd(task, goal);
            meansEnd.setId(UUID.randomUUID());
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
                parent.setSrElement(goal);
            }

            super.visit(n, arg);
        }

}
