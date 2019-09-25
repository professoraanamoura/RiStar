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
import java.util.UUID;
import ristar.elements.istar.Actor;
import ristar.elements.istar.ActorType;
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
public class Rule7 extends Rule {

    public Rule7() {
        super(null, null);
        setRuleNumber(7);
        setRuleDescription(" Transform method call for another library object in dependency.");
    }

    public Rule7(Model model, CompilationUnit cu) {
        super(model, cu);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        try {
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
                

                if (mce.resolve().getClassName() != null) {
                    //corrigir: verificar se a classe existe na lista de classes do sistema, caso contrário é uma classe de blioteca
                    Actor subTaskParent = (Actor) this.getModel().getNode(mce.resolve().getClassName(),Actor.class);

                    if (subTaskParent == null) {
                        //cria um ator para representar a biblioteca chamada
                        Actor libraryActor = new Actor();
                        libraryActor.setId(UUID.randomUUID());
                        libraryActor.setName(mce.resolve().getClassName());
                        libraryActor.setType(ActorType.General);
                        this.getModel().addNode(libraryActor);
                        //cria a dependência
                        Dependency dependency = new Dependency();
                        dependency.setId(UUID.randomUUID());
                        dependency.setDependee(libraryActor);
                        dependency.setDepender(task);
                        Dependum dependum = new Dependum();
                        dependum.setId(UUID.randomUUID());
                        dependum.setType(IntentionalType.Goal);
                        //definição do nome da meta que será atingida através da subtarefa
                        StringBuffer parameters = new StringBuffer();

                        dependum.setName(mce.resolve().getSignature()
                                + " be accomplished. ");
                        dependency.setDependum(dependum);
                        dependum.setDependency(dependency);
                        DependumLink dependumLink = new DependumLink(dependum);
                        dependumLink.setId(UUID.randomUUID());
                        this.getModel().addNode(dependum);

                    } else if(subTaskParent.getProperty("selected")==null ){
                        
                        Dependum dependum = null;
                        //verifica se a subtarefa já existe
                        if (this.getModel().getNode(mce.resolve().getSignature()
                                + " be accomplished. ") != null) {
                            dependum = (Dependum) this.getModel().getNode(mce.resolve().getSignature()
                                    + " be accomplished. ");
                        } else {
                            dependum = new Dependum();
                            dependum.setId(UUID.randomUUID());
                            dependum.setType(IntentionalType.Goal);
                            dependum.setName(mce.resolve().getSignature()
                                + " be accomplished. ");
                        }
                        //cria a dependência
                        Dependency dependency = new Dependency();
                        dependency.setId(UUID.randomUUID());
                        dependency.setDependee(subTaskParent);
                        dependency.setDepender(task);
                        //definição do nome da meta que será atingida através da subtarefa
                        StringBuffer parameters = new StringBuffer();                        
                        dependency.setDependum(dependum);
                        dependum.setDependency(dependency);
                        DependumLink dependumLink = new DependumLink(dependum);
                        dependumLink.setId(UUID.randomUUID());
                        this.getModel().addNode(dependum);

                    }
                }
            }
            );

        } catch (Exception fne) {
            fne.printStackTrace();
        }

        super.visit(n, arg);
    }
}
