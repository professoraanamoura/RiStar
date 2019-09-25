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
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import java.util.UUID;
import ristar.elements.istar.Actor;
import ristar.elements.istar.Model;
import ristar.elements.istar.SRElement;
import ristar.elements.istar.TaskDecomposition;

/**
 *
 * @author anamm
 */
public class Rule6 extends Rule {

    public Rule6() {
        super(null, null);
        setRuleNumber(6);
        setRuleDescription(" Transform method call in decomposition");
    }

    public Rule6(Model model, CompilationUnit cu) {
        super(model, cu);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
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
        final SRElement task = parent.getNode(n.getNameAsString());

        n
                .findAll(FieldAccessExpr.class
                ).forEach(be -> {

                    SRElement resourceFor = null;

                    if (parent != null) {
                        resourceFor = parent.getNode(be.getNameAsString());

                        if (resourceFor != null) {
                            TaskDecomposition decomposition = new TaskDecomposition(resourceFor, task);
                            decomposition.setId(UUID.randomUUID());
                        }
                    }

                });
        n
                .findAll(MethodCallExpr.class
                ).forEach(be -> {
                    SRElement subTask = null;

                    if (parent != null) {
                        subTask = parent.getNode(be.getNameAsString());
                        if (subTask != null) {

                            TaskDecomposition decomposition = new TaskDecomposition(subTask, task);
                            decomposition.setId(UUID.randomUUID());
                        }
                    }

                });
        super.visit(n, arg);
    }
}
