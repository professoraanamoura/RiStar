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
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import ristar.elements.istar.Actor;
import ristar.elements.istar.Dependency;
import ristar.elements.istar.Dependum;
import ristar.elements.istar.DependumLink;
import ristar.elements.istar.IntentionalType;
import ristar.elements.istar.Model;
import ristar.elements.istar.SRElement;

/**
 *
 * @author anamm
 */
public class Rule5 extends Rule {

    public Rule5() {
        super(null, null);
        setRuleNumber(5);
        setRuleDescription(" Transform method call for another system object in dependency.");

    }

    public Rule5(Model model, CompilationUnit cu) {
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
        //localizando as chamadas de métodos

        n.findAll(MethodCallExpr.class).forEach(mce -> {
            try {
                if (mce.resolve().getClassName() != null) {
                    Actor subTaskParent = (Actor) this.getModel().getNode(mce.resolve().getClassName());

                    SRElement subTask = null;

                    if (subTaskParent != null && !subTaskParent.equals(parent)) {
                        subTask = subTaskParent.getNode(mce.getNameAsString());
                        if (subTask != null) {
                            Dependency dependency = new Dependency();
                            dependency.setId(UUID.randomUUID());
                            dependency.setDependee(subTask);
                            dependency.setDepender(task);
                            Dependum dependum = new Dependum();
                            dependum.setId(UUID.randomUUID());
                            dependum.setType(IntentionalType.Goal);
                            //definição do nome da meta que será atingida através da subtarefa
                            StringBuffer parameters = new StringBuffer();
                            for (Node parameter : mce.getArguments()) {
                                parameters.append(((Expression) parameter).toString() + ",");
                            }
                            dependum.setName(subTask.getName()
                                    + (parameters.length() > 0 ? " by " + parameters.toString() : "")
                                    + " be accomplished. ");
                            dependency.setDependum(dependum);
                            dependum.setDependency(dependency);
                            DependumLink dependumLink = new DependumLink(dependum);
                            dependumLink.setId(UUID.randomUUID());
                            this.getModel().addNode(dependum);

                        }
                    }
                }
            } catch (UnsolvedSymbolException use) {
                Logger.getLogger(Rule5.class.getName()).log(Level.SEVERE, null, use);
            } catch (Exception ex) {
                Logger.getLogger(Rule5.class.getName()).log(Level.SEVERE, null, ex);

            }finally{
                return;
            }
        });

        /*NameExpr receiver = (NameExpr) n.getChildNodes().get(0);
            //identificando o No pai   
            String parentName = null;
            Node parentNode = n.getParentNode().get();
            while (!(parentNode instanceof ClassOrInterfaceDeclaration)) {
                parentNode = parentNode.getParentNode().get();
            }
            if (parentNode != null) {
                parentName = ((ClassOrInterfaceDeclaration) parentNode).getNameAsString();
            }
            Actor parent = (Actor) modelo.getNode(parentName);
            SRElement resourceFor = null;

            if (parent != null) {
                resourceFor = parent.getNode(receiver.getNameAsString());
                Node taskNode = n.getParentNode().get();
                while (!(parentNode instanceof MethodDeclaration) || !(parentNode instanceof ClassOrInterfaceDeclaration)) {
                    taskNode = taskNode.getParentNode().get();
                }
                SRElement task = parent.getNode(((MethodDeclaration) taskNode).getNameAsString());

                if ((resourceFor != null) && (task != null)) {
                    TaskDecomposition decomposition = new TaskDecomposition(resourceFor, task);
                    decomposition.setId(UUID.randomUUID());
                }
            }*/
        super.visit(n, arg);
    }

}
